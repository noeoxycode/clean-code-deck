package fr.cleancode.org.domain.hero.ports.client;

import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;

public interface HeroCreatorApi {
    Hero save(Hero hero);

    List<Hero> saveAll(List<Hero> heroes);
}