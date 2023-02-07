package fr.cleancode.org.domain.functional.service.validation;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static fr.cleancode.org.domain.functional.model.hero.Rarity.LEGENDARY;
import static fr.cleancode.org.domain.functional.model.hero.Speciality.TANK;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

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
                .speciality(TANK)
                .rarity(LEGENDARY)
                .level(1)
                .build();
        val actual = HeroValidator.validate(hero);
        assertThat(actual).containsValidInstanceOf(Hero.class);
    }

    @Test
    void should_not_validate() {
        val actual = HeroValidator.validate(Hero.builder().build());
        assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
    }
}