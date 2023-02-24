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
public class FightActionsService implements FightUtils {

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

}
