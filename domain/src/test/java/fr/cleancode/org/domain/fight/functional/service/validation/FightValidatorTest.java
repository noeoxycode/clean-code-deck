package fr.cleancode.org.domain.fight.functional.service.validation;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.player.functional.model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

public class FightValidatorTest {

    @Test
    public void test_validate_with_valid_fight_returns_true() {
        Hero attacker = Hero.builder().heroId(UUID.randomUUID()).level(5).name("Attacker").build();
        Hero defender = Hero.builder().heroId(UUID.randomUUID()).level(5).name("Defender").build();
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(attacker);
        Player player = Player.builder().deck(heroes).build();
        Fight fight = Fight.builder().attacker(attacker.getHeroId()).defender(defender.getHeroId())
                .winner(attacker.getHeroId()).build();

        boolean result = FightValidator.validate(player, fight, attacker, defender);

        Assertions.assertTrue(result);
    }

    @Test
    public void test_validate_with_null_attacker_returns_false() {
        Hero attacker = Hero.builder().heroId(UUID.randomUUID()).level(5).name("Attacker").build();
        Hero defender = Hero.builder().heroId(UUID.randomUUID()).level(5).name("Defender").build();
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(attacker);
        Player player = Player.builder().deck(heroes).build();
        Fight fight = Fight.builder().attacker(null).defender(defender.getHeroId())
                .winner(attacker.getHeroId()).build();

        boolean result = FightValidator.validate(player, fight, attacker, defender);

        Assertions.assertFalse(result);
    }

    @Test
    public void test_validate_with_invalid_owner_returns_false() {
        Hero attacker = Hero.builder().build();
        Hero defender = Hero.builder().build();
        Player player = Player.builder().build();
        Fight fight = Fight.builder().build();

        boolean result = FightValidator.validate(player, fight, attacker, defender);

        Assertions.assertFalse(result);
    }


    @Test
    public void test_validate_with_unfair_matchup_returns_false() {
        Hero attacker = Hero.builder().heroId(UUID.randomUUID()).level(1).name("Attacker").build();
        Hero defender = Hero.builder().heroId(UUID.randomUUID()).level(5).name("Defender").build();
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(attacker);
        Player player = Player.builder().deck(heroes).build();
        Fight fight = Fight.builder().attacker(attacker.getHeroId()).defender(defender.getHeroId())
                .winner(attacker.getHeroId()).build();

        boolean result = FightValidator.validate(player, fight, attacker, defender);

        Assertions.assertTrue(result);
    }

}
