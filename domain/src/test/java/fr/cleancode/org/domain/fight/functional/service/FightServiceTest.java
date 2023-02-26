package fr.cleancode.org.domain.fight.functional.service;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
    UpdateAfterFightService updateAfterFightServiceMock;

    @InjectMocks
    UpdateAfterFightService updateAfterFightService;

    @Test
    void testFight_PlayerNotFound() {
        FightService fightService = new FightService(heroFinderService, playerFinderSpi, playerCreatorSpi, fightCreatorSpi, fightActionsService, updateAfterFightService);
        Fight fight = Fight.builder().build();
        UUID playerId = UUID.randomUUID();

        when(playerFinderSpi.findPlayerById(playerId)).thenReturn(Optional.empty());

        assertThrows(PlayerException.class, () -> fightService.saveFifght(fight, playerId));
        verify(heroFinderService, never()).findHeroById(any(UUID.class));
        verify(fightUtilsService, never()).fightAction(any(Hero.class), any(Hero.class));
        verify(updateAfterFightServiceMock, never()).updatePlayerAfterFight(any(Player.class), any(Fight.class));
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

        FightException ex = assertThrows(FightException.class, () -> fightService.saveFifght(fight, playerId));
        assertEquals("Attacker not found", ex.getMessage());

        verify(heroFinderService, times(1)).findHeroById(heroId);
        verify(fightUtilsService, never()).fightAction(any(Hero.class), any(Hero.class));
        verify(updateAfterFightServiceMock, never())
                .updatePlayerAfterFight(
                        any(Player.class),
                        any(Fight.class));
        verify(fightCreatorSpi, never()).save(any(Fight.class));
    }

    @Test
    public void test_update_hero_statistics_after_win_should_increase_hero_stats() {
        Hero hero = Hero.builder().name("pigeon").level(1).power(10).armor(5).healthPoints(100).currentExperiences(3).build();
        Fight fight = Fight.builder().attacker(hero.getHeroId()).winner(hero.getHeroId()).build();
        hero = updateAfterFightService.updateHeroStatisticsAfterFight(hero, fight);
        assertEquals(4, hero.getCurrentExperiences());
        assertEquals(1, hero.getLevel());
        assertEquals(100, hero.getHealthPoints());
        assertEquals(10, hero.getPower());
        assertEquals(5, hero.getArmor());
    }

    @Test
    public void test_update_hero_statistics_after_win_should_level_up() {
        Hero hero = Hero.builder().heroId(UUID.randomUUID()).level(1).power(10).armor(5).healthPoints(100).currentExperiences(4).build();
        Fight fight = Fight.builder().winner(hero.getHeroId()).attacker(hero.getHeroId()).build();
        hero = updateAfterFightService.updateHeroStatisticsAfterFight(hero, fight);
        assertEquals(0, hero.getCurrentExperiences());
        assertEquals(2, hero.getLevel());
        assertEquals(110, hero.getHealthPoints());
        assertEquals(11, hero.getPower());
        assertEquals(6, hero.getArmor());
    }

}
