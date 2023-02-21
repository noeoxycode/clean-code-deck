package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.exception.FightException;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.functional.service.validation.FightValidator;
import fr.cleancode.org.domain.fight.port.client.FightApi;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.client.PlayerFinderApi;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FightService implements FightApi {

    private final HeroFinderApi heroFinderApi;

    private final PlayerFinderApi playerFinderApi;

    private final PlayerCreatorSpi playerCreatorSpi;

    private final FightCreatorSpi fightCreatorSpi;

    private final FightFinderSpi fightFinderSpi;

    public Fight fight(Fight fight, UUID playerId) {
        Player player = playerFinderApi.findPlayerById(playerId);
        Hero attacker = heroFinderApi.findHeroById(fight.getAttacker())
                .orElseThrow(() -> new FightException("Attacker not found"));
        Hero defender = heroFinderApi.findHeroById(fight.getDefender())
                .orElseThrow(() -> new FightException("Defender not found"));
        UUID winner = fight(attacker, defender);
        fight.setWinner(winner);
        FightValidator.validate(player, fight, attacker, defender);
        updatePlayerAndHero(player, fight, attacker, winner);
        fightCreatorSpi.save(fight);
        return fight;
    }

    private UUID fight(Hero attacker, Hero defender) {
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

    private void updatePlayerAndHero(Player player, Fight fight, Hero attacker, UUID winner) {
        if (winner.equals(attacker.getHeroId())) {
            updateHero(attacker);
            updatePlayerDeck(player, attacker);
            if(player.getFights() != null){
                List<UUID> newHistoFights = new ArrayList<>(player.getFights());
                newHistoFights.add(fight.getFightId());
                player.setFights(newHistoFights);
            }
            earningToken(player);
        }
        playerCreatorSpi.save(player);
    }

    private void updateHero(Hero hero) {
        if (hero.getCurrentExperiences() >= 4) {
            hero.setCurrentExperiences(0);
            hero.setLevel(hero.getLevel() + 1);
            hero.setHealthPoints( (int) Math.round(hero.getHealthPoints()*1.1));
            hero.setPower( (int) Math.round(hero.getPower()*1.1));
            hero.setArmor( (int) Math.round(hero.getArmor()*1.1));
        }
        else {
            hero.setCurrentExperiences(hero.getCurrentExperiences() + 1);
        }
    }

    private void updatePlayerDeck(Player player, Hero updatedHero) {
        List<Hero> deck = player.getDeck();
        int index = -1;
        for(int i = 0; i < deck.size(); i++){
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

    private void earningToken(Player player){
        List<Fight> fightHistory = fightFinderSpi.findAllFights();
        List<UUID> playersHeroesId = new ArrayList<>();
        for(Hero hero : player.getDeck()){
            playersHeroesId.add(hero.getHeroId());
        }
        long count = fightHistory.stream()
                .filter(fight -> playersHeroesId.contains(fight.getWinner()))
                .count();
        System.out.println(count);
        if(count%5 == 0 && count != 0){
            player.setToken(player.getToken()+1);
        }
    }

}
