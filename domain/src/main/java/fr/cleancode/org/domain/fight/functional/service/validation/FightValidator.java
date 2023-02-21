package fr.cleancode.org.domain.fight.functional.service.validation;

import fr.cleancode.org.domain.fight.functional.exception.FightException;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.player.functional.model.Player;

public interface FightValidator {

    static void validate(Player player, Fight fight, Hero attacker, Hero defender) {
        fightParametersChecker(player, fight, attacker, defender);
        if (!(fight.getDate() != null
                && fight.getAttacker() != null
                && fight.getDefender() != null
                && fight.getWinner() != null)) {
            throw new FightException("Bad Fight data");
        }
    }

    static void fightParametersChecker(Player player, Fight fight, Hero attacker, Hero defender) {
        ownerChecking(player, fight);
        fairMatchupChecker(attacker, defender);
    }

    private static void fairMatchupChecker(Hero attacker, Hero defender) {
        if (attacker.getLevel() > defender.getLevel()) {
            throw new FightException("Fight not allowed : the level differencial is too big");
        }
    }

    private static void ownerChecking(Player attacker, Fight fight) {
        boolean playerAttacksWithHisMonster = false;
        boolean playerDoesntAttackHisOwnMonster = true;
        for (Hero hero : attacker.getDeck()) {
            if (hero.getHeroId().equals(fight.getAttacker())) {
                playerAttacksWithHisMonster = true;
            }
            if (hero.getHeroId().equals(fight.getDefender())) {
                playerDoesntAttackHisOwnMonster = false;
            }
        }
        if (!playerAttacksWithHisMonster) {
            throw new FightException("Fight not allowed : impossible to attack with a hero that you do not own");
        }
        ;
        if (!playerDoesntAttackHisOwnMonster) {
            throw new FightException("Fight not allowed : impossible to attack a hero that you own");
        }
    }

}
