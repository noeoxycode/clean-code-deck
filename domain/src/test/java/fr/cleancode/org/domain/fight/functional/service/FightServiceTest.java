package fr.cleancode.org.domain.fight.functional.service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import fr.cleancode.org.domain.fight.functional.exception.FightException;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.service.HeroFinderService;
import fr.cleancode.org.domain.player.functional.exception.PlayerException;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FightServiceTest {

    @Mock
    HeroFinderService heroFinderService;

    @Mock
    PlayerFinderSpi playerFinderSpi;

    @Mock
    PlayerCreatorSpi playerCreatorSpi;

    @Mock
    FightCreatorSpi fightCreatorSpi;

    @Mock
    FightActionsService fightUtilsService;

    @Mock
    FightActionsService fightActionsService;

    @Mock
    UpdateAfterFightService updateAfterFightService;

    @Mock
    FightService fightService;

    @Test
    void testFight_PlayerNotFound() {
        FightService fightService = new FightService(heroFinderService, playerFinderSpi, playerCreatorSpi, fightCreatorSpi, fightActionsService, updateAfterFightService);
        Fight fight = Fight.builder().build();
        UUID playerId = UUID.randomUUID();

        when(playerFinderSpi.findPlayerById(playerId)).thenReturn(Optional.empty());

        assertThrows(PlayerException.class, () -> fightService.fight(fight, playerId));
        verify(heroFinderService, never()).findHeroById(any(UUID.class));
        verify(fightUtilsService, never()).fight(any(Hero.class), any(Hero.class));
        verify(updateAfterFightService, never()).updatePlayerAndHeroAfterFightWon(any(Player.class), any(Fight.class), any(Hero.class), any(UUID.class));
        verify(fightCreatorSpi, never()).save(any(Fight.class));
    }

    @Test
    void testFight_HeroNotFound() {
        UUID heroId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();

        FightService fightService = new FightService(heroFinderService, playerFinderSpi, playerCreatorSpi, fightCreatorSpi, fightActionsService, updateAfterFightService);
        Fight fight = Fight.builder()
                .attacker(heroId)
                .build();

        Player player = Player.builder().build();

        when(playerFinderSpi.findPlayerById(playerId)).thenReturn(Optional.of(player));
        when(heroFinderService.findHeroById(heroId)).thenReturn(Optional.empty());

        FightException ex = assertThrows(FightException.class, () -> fightService.fight(fight, playerId));
        assertEquals("Attacker not found", ex.getMessage());

        verify(heroFinderService, times(1)).findHeroById(heroId);
        verify(fightUtilsService, never()).fight(any(Hero.class), any(Hero.class));
        verify(updateAfterFightService, never())
                .updatePlayerAndHeroAfterFightWon(
                        any(Player.class),
                        any(Fight.class),
                        any(Hero.class),
                        any(UUID.class));
        verify(fightCreatorSpi, never()).save(any(Fight.class));
    }
}
