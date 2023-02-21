package fr.cleancode.org.domain.hero.ports.client;

import fr.cleancode.org.domain.hero.functional.model.Hero;

public interface HeroCreatorApi {
    Hero save(Hero hero);
}