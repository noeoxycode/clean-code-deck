package fr.cleancode.org.domain.hero.functional.service.initialization;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.functional.model.Speciality;

public interface HeroInitializer {

    static Hero initializeHeroPropertiesBySpeciality(Hero hero) {
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

    static Hero initializeHeroPropertiesByRarity(Hero hero) {
        Rarity rarity = hero.getRarity();
        switch (rarity) {
            case COMMON -> {
                hero.setHealthPoints((int) (hero.getHealthPoints() * (float) 1));
                hero.setPower((int) (hero.getPower() * (float) 1));
                hero.setArmor((int) (hero.getArmor() * (float) 1));
            }
            case RARE -> {
                hero.setHealthPoints((int) (hero.getHealthPoints() * (float) 1.1));
                hero.setPower((int) (hero.getPower() * (float) 1.1));
                hero.setArmor((int) (hero.getArmor() * (float) 1.1));
            }
            case LEGENDARY -> {
                hero.setHealthPoints((int) (hero.getHealthPoints() * (float) 1.2));
                hero.setPower((int) (hero.getPower() * (float) 1.2));
                hero.setArmor((int) (hero.getArmor() * (float) 1.2));
            }
        }
        return hero;
    }

}