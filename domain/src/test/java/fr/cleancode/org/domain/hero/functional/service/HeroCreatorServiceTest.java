package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.domain.hero.ports.server.HeroPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static fr.cleancode.org.domain.hero.functional.model.Rarity.LEGENDARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
                .speciality(Speciality.TANK)
                .rarity(LEGENDARY)
                .level(1)
                .build();
        when(spi.create(given)).thenReturn(given);

        val actual = service.create(given);
        assertThat(actual)
                .isEqualTo(given)
                .usingRecursiveComparison();
    }

//    @Test
//    void should_not_create_hero_if_is_null() {
//        val given = Hero.builder()
//                .currentExperiences(0)
//                .speciality(Speciality.TANK)
//                .build();
//        val actual = service.create(given);
//        assertThat(actual).isInstanceOf(ApplicationError.class);
//        verifyNoInteractions(spi);
//    }
}