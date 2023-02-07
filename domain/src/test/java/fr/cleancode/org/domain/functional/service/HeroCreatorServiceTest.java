package fr.cleancode.org.domain.functional.service;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import fr.cleancode.org.domain.ports.server.HeroPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static fr.cleancode.org.domain.functional.model.hero.Rarity.LEGENDARY;
import static fr.cleancode.org.domain.functional.model.hero.Speciality.TANK;
import static io.vavr.API.Right;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HeroCreatorServiceTest {

    @InjectMocks
    private HeroCreatorService service;
    @Mock
    private HeroPersistenceSpi spi;

    @Test
    void should_create_hero() {
        val given = Hero.builder()
                .heroId(UUID.randomUUID())
                .name("Dragon")
                .healthPoints(1000)
                .currentExperiences(10)
                .power(100)
                .armor(200)
                .speciality(TANK)
                .rarity(LEGENDARY)
                .level(1)
                .build();
        when(spi.save(given)).thenReturn(Right(given));

        val actual = service.create(given);
        assertThat(actual).containsRightSame(given);
    }

    @Test
    void should_not_create_hero_if_is_null() {
        val given = Hero.builder().build();
        val actual = service.create(given);
        assertThat(actual).containsLeftInstanceOf(ApplicationError.class);
        verifyNoInteractions(spi);
    }
}