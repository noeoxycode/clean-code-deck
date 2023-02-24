package fr.cleancode.org.domain.money.functional.service;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.money.EarningTokenService;
import fr.cleancode.org.domain.player.functional.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EarningTokenServiceTest {


    @Mock
    private FightFinderSpi fightFinderSpi;

    @InjectMocks
    private EarningTokenService earningTokenService;

    @Test
    void earningToken_should_add_token_to_player() {
        // Arrange
        Hero hero1 = Hero.builder().heroId(UUID.randomUUID()).name("hero1").build();
        Hero hero2 = Hero.builder().heroId(UUID.randomUUID()).name("hero2").build();
        ArrayList<Hero> deck = new ArrayList<>();
        deck.add(hero1);
        deck.add(hero2);
        Player player = Player.builder().playerId(UUID.randomUUID()).token(0).deck(deck).build();
        List<Fight> fightHistory = Arrays.asList(
                Fight.builder().winner(hero1.getHeroId()).build(),
                Fight.builder().winner(hero2.getHeroId()).build(),
                Fight.builder().winner(hero1.getHeroId()).build(),
                Fight.builder().winner(hero1.getHeroId()).build(),
                Fight.builder().winner(hero2.getHeroId()).build()
        );
        when(fightFinderSpi.findAllFights()).thenReturn(fightHistory);

        // Act
        Player updatedPlayer = earningTokenService.earningToken(player);

        // Assert
        assertEquals(1, updatedPlayer.getToken());
    }

    @Test
    void earningToken_should_not_add_token_to_player() {
        // Arrange
        Hero hero1 = Hero.builder().heroId(UUID.randomUUID()).name("hero1").build();
        Hero hero2 = Hero.builder().heroId(UUID.randomUUID()).name("hero2").build();
        ArrayList<Hero> deck = new ArrayList<>();
        deck.add(hero1);
        deck.add(hero2);
        Player player = Player.builder().playerId(UUID.randomUUID()).token(0).deck(deck).build();
        UUID idOtherPlayer = UUID.randomUUID();
        List<Fight> fightHistory = Arrays.asList(
                Fight.builder().winner(idOtherPlayer).build(),
                Fight.builder().winner(idOtherPlayer).build()
        );
        when(fightFinderSpi.findAllFights()).thenReturn(fightHistory);

        // Act
        Player updatedPlayer = earningTokenService.earningToken(player);

        // Assert
        assertEquals(0, updatedPlayer.getToken());
    }

}
