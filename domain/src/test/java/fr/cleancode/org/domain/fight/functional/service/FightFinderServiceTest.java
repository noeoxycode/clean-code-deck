package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FightFinderServiceTest {

    @Mock
    private FightFinderSpi fightFinderSpi;

    @Test
    void test_find_fights_fistory_returns_empty_optional_when_hero_has_no_fights() {
        FightHistoryFinderService fightFinderService = new FightHistoryFinderService(fightFinderSpi);
        UUID heroId = UUID.randomUUID();
        when(fightFinderSpi.findHeroFightsHistory(heroId)).thenReturn(Optional.of(new ArrayList<>()));

        Optional<List<Fight>> result = fightFinderService.findFightsHistory(heroId);

        assertTrue(result.isEmpty());
    }

    @Test
    void test_find_fights_history_returns_hero_fight_history_when_hero_has_fights() {
        FightHistoryFinderService fightFinderService = new FightHistoryFinderService(fightFinderSpi);
        UUID heroId = UUID.randomUUID();
        List<Fight> fights = new ArrayList<>();
        fights.add(Fight.builder().build());
        when(fightFinderSpi.findHeroFightsHistory(heroId)).thenReturn(Optional.of(fights));

        Optional<List<Fight>> result = fightFinderService.findFightsHistory(heroId);

        assertFalse(result.isEmpty());
        assertEquals(fights, result.get());
    }

    @Test
    void test_find_fights_history_throws_exception_when_spi_returns_null() {
        FightHistoryFinderService fightFinderService = new FightHistoryFinderService(fightFinderSpi);
        UUID heroId = UUID.randomUUID();
        when(fightFinderSpi.findHeroFightsHistory(heroId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> {
            fightFinderService.findFightsHistory(heroId);
        });
    }

}
