package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FightUtilsService {

    private final PlayerCreatorSpi playerCreatorSpi;

    private final FightFinderSpi fightFinderSpi;

    public UUID fight(Hero attacker, Hero defender) {
        attacker.setPower(attacker.getPower() + calculateMatchupBuff(attacker, defender));
        defender.setPower(defender.getPower() + calculateMatchupBuff(defender, attacker));
        int turn = 0;
        while (attacker.getHealthPoints() > 0 && defender.getHealthPoints() > 0) {
            if (turn % 2 == 0) {
                attack(attacker, defender);
            } else {
                attack(defender, attacker);
            }
            turn++;
        }
        return attacker.getHealthPoints() > 0 ? attacker.getHeroId() : defender.getHeroId();
    }

    public void updatePlayerAndHeroAfterFightWon(Player player, Fight fight, Hero attacker, UUID winner) {
        if (winner.equals(attacker.getHeroId())) {
            updateHeroStatisticsAfterWin(attacker);
            updateHeroInDeck(player, attacker);
            ArrayList<UUID> newHistoFights = new ArrayList<>();
            if (player.getFights() != null) {
                newHistoFights = new ArrayList<>(player.getFights());
                newHistoFights.add(fight.getFightId());
                player.setFights(newHistoFights);
            } else {
                newHistoFights.add(fight.getFightId());
                player.setFights(newHistoFights);
            }
            earningToken(player);
        }
        playerCreatorSpi.save(player);
    }

    private int calculateMatchupBuff(Hero attacker, Hero defender) {
        if (attacker.getSpeciality() == Speciality.TANK && defender.getSpeciality() == Speciality.MAGE) {
            return 20;
        } else if (attacker.getSpeciality() == Speciality.ASSASSIN && defender.getSpeciality() == Speciality.TANK) {
            return 30;
        } else if (attacker.getSpeciality() == Speciality.MAGE && defender.getSpeciality() == Speciality.ASSASSIN) {
            return 25;
        } else {
            return 0;
        }
    }

    private void attack(Hero attacker, Hero defender) {
        defender.setHealthPoints(defender.getHealthPoints() - (attacker.getPower() - defender.getArmor()));
    }

    private void updateHeroStatisticsAfterWin(Hero hero) {
        if (hero.getCurrentExperiences() >= 4) {
            hero.setCurrentExperiences(0);
            hero.setLevel(hero.getLevel() + 1);
            hero.setHealthPoints((int) Math.round(hero.getHealthPoints() * 1.1));
            hero.setPower((int) Math.round(hero.getPower() * 1.1));
            hero.setArmor((int) Math.round(hero.getArmor() * 1.1));
        } else {
            hero.setCurrentExperiences(hero.getCurrentExperiences() + 1);
        }
    }

    private void updateHeroInDeck(Player player, Hero updatedHero) {
        ArrayList<Hero> deck = player.getDeck();
        int index = -1;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getHeroId().equals(updatedHero.getHeroId())) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            deck.set(index, updatedHero);
            player.setDeck(deck);
        }
    }

    void earningToken(Player player) {
        List<Fight> fightHistory = fightFinderSpi.findAllFights();
        List<UUID> playersHeroesId = new ArrayList<>();
        for (Hero hero : player.getDeck()) {
            playersHeroesId.add(hero.getHeroId());
        }
        long count = fightHistory.stream()
                .filter(fight -> playersHeroesId.contains(fight.getWinner()))
                .count();
        if (count % 5 == 0 && count != 0) {
            player.setToken(player.getToken() + 1);
        }
    }


}
