package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.hero.ports.server.HeroPersistenceSpi;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HeroFinderServiceTest {

    @InjectMocks
    private HeroFinderService service;

    @Mock
    private HeroPersistenceSpi spi;


//    @Test
//    void should_not_find_hero() {
//        final var id = UUID.randomUUID();
//
//        when(spi.findById(id)).thenReturn(null);
//
//        final var actual = service.findHeroById(id);
//
//        assertNull(actual);
//        verify(spi).findById(id);
//        verifyNoMoreInteractions(spi);
//    }
//
//    @Test
//    void should_find_hero() {
//        final var id = UUID.randomUUID();
//        final var hero = Hero.builder().heroId(id).build();
//
//        when(spi.findById(id)).thenReturn(hero);
//
//        final var actual = service.findHeroById(id);
//        Assertions.assertEquals(actual, hero);
//        verify(spi).findById(id);
//        verifyNoMoreInteractions(spi);
//    }
}
