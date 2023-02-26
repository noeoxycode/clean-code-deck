package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.money.EarningTokenService;
import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UpdateAfterFightService implements FightUtils {
    private final EarningTokenService earningTokenService;

    public Player updatePlayerAfterFight(Player player, Fight fight) {
        ArrayList<UUID> newHistoFights = new ArrayList<>();
        if (player.getFights() != null) {
            newHistoFights = player.getFights();
            newHistoFights.add(fight.getFightId());
            player.setFights(newHistoFights);
        } else {
            newHistoFights.add(fight.getFightId());
            player.setFights(newHistoFights);
        }
        player = earningTokenService.earningToken(player);
        return player;
    }

    public Hero updateHeroStatisticsAfterFight(Hero hero, Fight fight) {
        if(fight.getAttacker().equals(hero.getHeroId())){
            if(fight.getAttacker().equals(fight.getWinner())){
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
        }

        return hero;
    }


}
