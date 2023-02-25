package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FightActionServiceTest {

    @Test
    public void test_fight_attacker_wins() {
        Hero attacker = Hero.builder().power(10).healthPoints(100).armor(5).build();
        Hero defender = Hero.builder().power(10).healthPoints(100).armor(5).build();

        FightActionsService fightActionsService = new FightActionsService();

        UUID winnerId = fightActionsService.fightAction(attacker, defender);

        assertEquals(winnerId, attacker.getHeroId());
    }
    @Test
    public void test_fight_defender_wins() {
        Hero attacker = Hero.builder().power(0).healthPoints(1).armor(5).build();
        Hero defender = Hero.builder().power(10).healthPoints(100).armor(5).build();

        FightActionsService fightActionsService = new FightActionsService();

        UUID winnerId = fightActionsService.fightAction(attacker, defender);

        assertEquals(winnerId, defender.getHeroId());
    }


}
