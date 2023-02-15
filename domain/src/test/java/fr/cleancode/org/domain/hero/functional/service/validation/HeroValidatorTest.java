package fr.cleancode.org.domain.hero.functional.service.validation;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class HeroValidatorTest {

    @Test
    void should_validate() {
        val hero = Hero.builder()
                .heroId(UUID.randomUUID())
                .name("Dragon")
                .healthPoints(1000)
                .currentExperiences(10)
                .power(100)
                .armor(200)
                .speciality(Speciality.TANK)
                .rarity(Rarity.LEGENDARY)
                .level(1)
                .build();
        val actual = HeroValidator.validate(hero);
        assertThat(actual).isInstanceOf(Hero.class);
    }

//    @Test
//    void should_not_validate() {
//        val actual = HeroValidator.validate(Hero.builder().build());
//        assertThat(actual).isInstanceOf(ApplicationError.class);
//    }
}