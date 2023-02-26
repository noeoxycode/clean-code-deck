package fr.cleancode.org.domain.hero.ports.server;

import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;

public interface HeroCreatorSpi {

    Hero save(Hero hero);

    List<Hero> saveAll(List<Hero> heroes);
}