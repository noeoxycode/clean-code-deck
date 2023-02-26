package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static fr.cleancode.org.domain.hero.functional.model.Rarity.LEGENDARY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroFinderServiceTest {

    @InjectMocks
    private HeroFinderService heroFinderService;

    @Mock
    private HeroFinderSpi heroFinderSpi;

    @Mock
    private PlayerFinderSpi playerFinderSpi;

    @Test
    void should_find_heroes() {
        List<Hero> given = List.of(
                Hero.builder()
                        .heroId(UUID.randomUUID())
                        .name("Dragon")
                        .healthPoints(1000)
                        .currentExperiences(10)
                        .power(100)
                        .armor(200)
                        .speciality(Speciality.TANK)
                        .rarity(LEGENDARY)
                        .level(1)
                        .build(),
                Hero.builder()
                        .heroId(UUID.randomUUID())
                        .name("Dragon")
                        .healthPoints(1000)
                        .currentExperiences(10)
                        .power(100)
                        .armor(200)
                        .speciality(Speciality.TANK)
                        .rarity(LEGENDARY)
                        .level(1)
                        .build(),
                Hero.builder()
                        .heroId(UUID.randomUUID())
                        .name("Dragon")
                        .healthPoints(1000)
                        .currentExperiences(10)
                        .power(100)
                        .armor(200)
                        .speciality(Speciality.TANK)
                        .rarity(LEGENDARY)
                        .level(1)
                        .build()
        );

        when(heroFinderSpi.findAllHeroes()).thenReturn(given);

        final var actual = heroFinderService.findAllHeroes();

        assertEquals(actual, given);
        verify(heroFinderSpi).findAllHeroes();
        verifyNoMoreInteractions(heroFinderSpi);
    }

    @Test
    void should_not_find_heroes() {
        List<Hero> given = null;

        when(heroFinderSpi.findAllHeroes()).thenReturn(given);

        final var actual = heroFinderService.findAllHeroes();

        assertNull(actual);
        verify(heroFinderSpi).findAllHeroes();
        verifyNoMoreInteractions(heroFinderSpi);
    }

    @Test
    void test_find_all_owned_heroes_returns_empty_list_when_no_players_exist() {
        when(playerFinderSpi.findAllPlayers()).thenReturn(new ArrayList<>());

        List<Hero> result = heroFinderService.findAllOwnedHeroes();

        assertTrue(result.isEmpty());
    }

    @Test
    void test_find_all_owned_heroes_returns_all_heroes_when_players_exist() {
        List<Player> players = new ArrayList<>();
        Player player1 = Player.builder().build();
        Player player2 = Player.builder().build();
        Hero hero1 = Hero.builder().build();
        Hero hero2 = Hero.builder().build();
        ArrayList<Hero> player1Deck = new ArrayList<>();
        player1Deck.add(hero1);
        ArrayList<Hero> player2Deck = new ArrayList<>();
        player1Deck.add(hero2);
        player1.setDeck(player1Deck);
        player2.setDeck(player2Deck);
        players.add(player1);
        players.add(player2);
        when(playerFinderSpi.findAllPlayers()).thenReturn(players);

        List<Hero> result = heroFinderService.findAllOwnedHeroes();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertTrue(result.contains(hero1));
        assertTrue(result.contains(hero2));
    }

    @Test
    void test_find_all_owned_heroes_returns_all_heroes_when_players_exist_with_empty_decks() {
        List<Player> players = new ArrayList<>();
        Player player1 = Player.builder().build();
        Player player2 = Player.builder().build();
        players.add(player1);
        players.add(player2);
        when(playerFinderSpi.findAllPlayers()).thenReturn(players);

        List<Hero> result = heroFinderService.findAllOwnedHeroes();

        assertTrue(result.isEmpty());
    }

    @Test
    void test_find_hero_by_id_returns_empty_optional_when_no_heroes_exist() {
        when(playerFinderSpi.findAllPlayers()).thenReturn(new ArrayList<>());

        Optional<Hero> result = heroFinderService.findHeroById(UUID.randomUUID());

        assertTrue(result.isEmpty());
    }

    @Test
    void test_find_hero_by_id_returns_empty_optional_when_hero_not_found() {
        Player player = Player.builder().build();
        Hero hero = Hero.builder().build();
        ArrayList<Hero> deck = new ArrayList<>();
        deck.add(hero);
        player.setDeck(deck);
        when(playerFinderSpi.findAllPlayers()).thenReturn(Collections.singletonList(player));

        Optional<Hero> result = heroFinderService.findHeroById(UUID.randomUUID());

        assertTrue(result.isEmpty());
    }

    @Test
    void test_find_hero_by_id_returns_hero_when_found() {
        Player player = Player.builder().build();
        Hero hero = Hero.builder().build();
        ArrayList<Hero> deck = new ArrayList<>();
        deck.add(hero);
        player.setDeck(deck);
        when(playerFinderSpi.findAllPlayers()).thenReturn(Collections.singletonList(player));

        Optional<Hero> result = heroFinderService.findHeroById(hero.getHeroId());

        assertTrue(result.isPresent());
        assertEquals(hero, result.get());
    }

}
