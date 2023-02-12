package fr.cleancode.org.domain.functional.service;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import fr.cleancode.org.domain.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.ports.server.HeroPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static io.vavr.API.None;
import static io.vavr.API.Some;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class HeroFinderServiceTest {

    @InjectMocks private HeroFinderService service;

    @Mock private HeroPersistenceSpi spi;

    @Test
    void should_not_find_hero() {
        val hero = Hero.builder().build();
        val heroId = hero.getHeroId();

        when(spi.findById(heroId)).thenReturn(None());

        val actual = service.findHeroById(heroId);
        assertThat(actual).containsLeftInstanceOf(ApplicationError.class);
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_find_hero() {
        val hero = Hero.builder().build();
        val heroId = hero.getHeroId();

        when(spi.findById(heroId)).thenReturn(Some(hero));

        val actual = service.findHeroById(heroId);
        assertThat(actual).containsLeftInstanceOf(Hero.class);
        verifyNoMoreInteractions(spi);
    }


}
