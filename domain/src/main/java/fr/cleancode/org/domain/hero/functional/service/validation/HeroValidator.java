package fr.cleancode.org.domain.hero.functional.service.validation;

import fr.cleancode.org.domain.hero.functional.model.Hero;

public interface HeroValidator {

    static boolean validate(Hero hero) {
        return hero.getName() != null
                && hero.getSpeciality() != null
                && hero.getRarity() != null
                && hero.getLevel() >= 1
                && hero.getLevel() < 100;
    }
}