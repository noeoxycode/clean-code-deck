package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.domain.player.functional.model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FightUtilsTest {

    private final FightUtils fightUtils = new FightActionsService();

    @Test
    public void test_calculate_matchup_Buff_tank_vs_mage_returns_20() {
        Hero attacker = Hero.builder().speciality(Speciality.TANK).build();
        Hero defender = Hero.builder().speciality(Speciality.MAGE).build();
        int expectedBuff = 20;
        int actualBuff = fightUtils.calculateMatchupBuff(attacker, defender);
        assertEquals(expectedBuff, actualBuff);
    }

    @Test
    public void test_calculate_matchup_buff_assassin_vs_tank_returns_30() {
        Hero attacker = Hero.builder().speciality(Speciality.ASSASSIN).build();
        Hero defender = Hero.builder().speciality(Speciality.TANK).build();
        int expectedBuff = 30;
        int actualBuff = fightUtils.calculateMatchupBuff(attacker, defender);
        assertEquals(expectedBuff, actualBuff);
    }

    @Test
    public void test_calculate_matchup_buff_mage_vs_assassin_returns_25() {
        Hero attacker = Hero.builder().speciality(Speciality.MAGE).build();
        Hero defender = Hero.builder().speciality(Speciality.ASSASSIN).build();
        int expectedBuff = 25;
        int actualBuff = fightUtils.calculateMatchupBuff(attacker, defender);
        assertEquals(expectedBuff, actualBuff);
    }

    @Test
    public void test_calculate_matchup_buff_same_speciality_returns_0() {
        Hero attacker = Hero.builder().speciality(Speciality.TANK).build();
        Hero defender = Hero.builder().speciality(Speciality.TANK).build();
        int expectedBuff = 0;
        int actualBuff = fightUtils.calculateMatchupBuff(attacker, defender);
        assertEquals(expectedBuff, actualBuff);
    }

    @Test
    public void test_attack_reduces_defender_health_points() {
        Hero attacker = Hero.builder().power(10).build();
        Hero defender = Hero.builder().healthPoints(50).armor(0).build();

        fightUtils.attack(attacker, defender);

        assertEquals(40, defender.getHealthPoints());
    }

    @Test
    public void test_attack_reduces_defender_health_points_with_armor() {
        Hero attacker = Hero.builder().power(10).build();
        Hero defender = Hero.builder().healthPoints(50).armor(5).build();

        fightUtils.attack(attacker, defender);

        assertEquals(45, defender.getHealthPoints());
    }

    @Test
    public void test_update_hero_in_deck_existing_hero() {
        Hero hero1 = Hero.builder().heroId(UUID.randomUUID()).level(5).power(100000).name("Hero 1").build();
        Hero hero2 = Hero.builder().heroId(UUID.randomUUID()).level(5).power(100000).name("Hero 2").build();
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(hero1);
        heroes.add(hero2);
        Player player = Player.builder().deck(heroes).build();

        Hero updatedHero = Hero.builder().heroId(hero1.getHeroId()).level(6).power(110000).name("Updated Hero 1").build();
        fightUtils.updateHeroInDeck(player, updatedHero);

        assertEquals(updatedHero, player.getDeck().get(0));
    }

    @Test
    public void test_update_hero_in_deck_non_existingHero() {
        Hero hero1 = Hero.builder().heroId(UUID.randomUUID()).level(5).power(100000).name("Hero 1").build();
        Hero hero2 = Hero.builder().heroId(UUID.randomUUID()).level(5).power(100000).name("Hero 2").build();
        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(hero1);
        Player player = Player.builder().deck(heroes).build();

        Hero updatedHero = Hero.builder().heroId(hero2.getHeroId()).level(6).power(110000).name("Updated Hero 2").build();
        fightUtils.updateHeroInDeck(player, updatedHero);

        assertNull(player.getDeck().stream().filter(hero -> hero.getHeroId().equals(updatedHero.getHeroId())).findFirst().orElse(null));
    }

}
