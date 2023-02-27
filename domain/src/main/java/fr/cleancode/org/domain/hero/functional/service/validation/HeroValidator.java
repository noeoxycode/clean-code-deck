package fr.cleancode.org.domain.hero.functional.service.validation;

import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;

public interface HeroValidator {

    static boolean validate(Hero hero) {
        return hero.getName() != null
                && hero.getSpeciality() != null
                && hero.getRarity() != null
                && hero.getLevel() >= 1
                && hero.getLevel() < 100;
    }

    static boolean validateList(List<Hero> heroes) {
        if (heroes == null) {
            return false;
        }
        return heroes.size() > 0;
    }
}