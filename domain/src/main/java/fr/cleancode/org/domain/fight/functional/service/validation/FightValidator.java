package fr.cleancode.org.domain.fight.functional.service.validation;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface FightValidator {

    Logger logger
            = Logger.getLogger(
            FightValidator.class.getName());

    static boolean validate(Player player, Fight fight, Hero attacker, Hero defender) {
        if (fight.getFightDate() != null
                && fight.getAttacker() != null
                && fight.getDefender() != null
                && fight.getWinner() != null) {
            return (fightParametersChecker(player, fight, attacker, defender));
        }
        else {
            return false;
        }
    }

    private static boolean fightParametersChecker(Player player, Fight fight, Hero attacker, Hero defender) {
        if(heroesOwnerValidChecker(player, fight)){
            return fairMatchupChecker(attacker, defender);
        }
        else {
            logger.log(Level.INFO,"Fight not allowed : owner issue");
            return false;
        }
    }

    private static boolean fairMatchupChecker(Hero attacker, Hero defender) {
        if (attacker.getLevel() > defender.getLevel()) {
            logger.log(Level.INFO,"Fight not allowed : the level differencial is too big");
            return false;
        }
        return true;
    }

    private static boolean heroesOwnerValidChecker(Player attacker, Fight fight) {
        boolean playerAttacksWithHisMonster = false;
        boolean playerAttackerHisOwnMonster = true;
        for (Hero hero : attacker.getDeck()) {
            if (hero.getHeroId().equals(fight.getAttacker())) {
                playerAttacksWithHisMonster = true;
            }
            if (hero.getHeroId().equals(fight.getDefender())) {
                playerAttackerHisOwnMonster = false;
            }
        }
        if (!playerAttacksWithHisMonster) {
            logger.log(Level.INFO,"Fight not allowed : impossible to attack with a hero that you do not own");
            return false;
        }
        if (!playerAttackerHisOwnMonster) {
            logger.log(Level.INFO,"Fight not allowed : impossible to attack a hero that you own");
            return false;
        }
        return true;
    }

}
