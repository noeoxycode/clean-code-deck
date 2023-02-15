package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static fr.cleancode.org.domain.hero.functional.model.Rarity.LEGENDARY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroFinderServiceTest {

    @InjectMocks
    private HeroFinderService service;

    @Mock
    private HeroFinderSpi spi;

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

        when(spi.findAllHeroes()).thenReturn(given);

        final var actual = service.findAllHeroes();

        assertEquals(actual, given);
        verify(spi).findAllHeroes();
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_not_find_heroes() {
        List<Hero> given = null;

        when(spi.findAllHeroes()).thenReturn(given);

        final var actual = service.findAllHeroes();

        assertNull(actual);
        verify(spi).findAllHeroes();
        verifyNoMoreInteractions(spi);
    }
}
