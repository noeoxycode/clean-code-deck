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
                ? Valid(hero)
                : Invalid(new ApplicationError("All field shouldn't be null", null, hero, null));
    }

    // For now this class just validate if the hero is not null
    // In a second time i will increase this class to add more verifications
}