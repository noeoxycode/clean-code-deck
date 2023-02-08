package fr.cleancode.org.domain.functional.service.validation;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import io.vavr.control.Validation;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;

public interface HeroValidator {

    static Validation<ApplicationError, Hero> validate(Hero hero) {
        return hero.getName() != null
                && hero.getSpeciality() != null
                && hero.getRarity() != null
                && hero.getLevel() >= 1
                && hero.getLevel() < 100
                ? Valid(hero)
                : Invalid(new ApplicationError("Invalid field with the hero {}", null, hero, null));
    }
}