package fr.cleancode.org.domain.hero.functional.service.initialization;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;

public interface HeroInitializer {

    static Hero initializeHeroProperties(Hero hero) {
        Speciality speciality = hero.getSpeciality();
        switch (speciality) {
            case TANK -> {
                hero.setHealthPoints(1000);
                hero.setPower(100);
                hero.setArmor(20);
            }
            case ASSASSIN -> {
                hero.setHealthPoints(800);
                hero.setPower(200);
                hero.setArmor(5);
            }
            case MAGE -> {
                hero.setHealthPoints(700);
                hero.setPower(150);
                hero.setArmor(10);
            }
        }
        return hero;
    }
}