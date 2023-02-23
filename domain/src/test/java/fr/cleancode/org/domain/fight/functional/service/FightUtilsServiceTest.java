package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.service.HeroFinderService;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FightUtilsServiceTest {

    @Mock
    private PlayerCreatorSpi playerCreatorSpi;

    @Mock
    private FightFinderSpi fightFinderSpi;

    @Mock
    HeroFinderService heroFinderService;

    @Mock
    PlayerFinderSpi playerFinderSpi;

    @Mock
    FightCreatorSpi fightCreatorSpi;

    @Mock
    private FightUtilsService fightUtilsService;



    @Test
    public void testFight_ValidFight() {
        // Crée les objets à tester
        Hero attacker = Hero.builder().build();
        Hero defender = Hero.builder().build();
        UUID fightId = UUID.randomUUID();
        UUID winner = attacker.getHeroId();
        Player player = Player.builder().build();
        Fight fight = Fight.builder().build();
        FightService fightService = new FightService(heroFinderService, playerFinderSpi, fightCreatorSpi, fightUtilsService);

        // Configure les comportements des mocks
        when(fightUtilsService.fight(attacker, defender)).thenReturn(winner);
        doAnswer(invocation -> {
            Player p = invocation.getArgument(0);
            fightUtilsService.earningToken(p);
            return null;
        }).when(fightUtilsService).updatePlayerAndHeroAfterFightWon(any(Player.class), any(Fight.class), eq(attacker), eq(winner));

        when(playerCreatorSpi.save(player)).thenReturn(null);
        when(fightFinderSpi.findFightById(fightId)).thenReturn(Optional.ofNullable(fight));

        // Exécute la méthode à tester
        Fight actualFight = fightService.fight(fight, player.getPlayerId());

        // Vérifie que les comportements attendus ont été appelés sur les mocks
        verify(fightUtilsService).fight(attacker, defender);
        verify(fightUtilsService).updatePlayerAndHeroAfterFightWon(player, fight, attacker, winner);
        verify(playerCreatorSpi).save(player);
        verify(fightFinderSpi).findFightById(fightId);

        // Vérifie le résultat
        assertEquals(fight, actualFight);
    }

        /*@Test
    void updatePlayerAndHeroAfterFightWonTest() {
        Player player = Player.builder().build();
        Fight fight = Fight.builder().build();
        Hero attacker = Hero.builder().build();
        UUID winner = UUID.randomUUID();

        when(fightFinderSpi.findAllFights()).thenReturn(new ArrayList<>());
        when(playerCreatorSpi.save(player)).thenReturn(player);

        fightUtilsService.updatePlayerAndHeroAfterFightWon(player, fight, attacker, winner);

        verify(playerCreatorSpi).save(player);

        assertEquals(1, player.getFights().size());
        assertEquals(fight.getFightId(), player.getFights().get(0));
    }*/

}
