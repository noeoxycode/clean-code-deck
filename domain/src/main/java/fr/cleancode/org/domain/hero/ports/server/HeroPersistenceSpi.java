package fr.cleancode.org.domain.hero.ports.server;

import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;

public interface HeroPersistenceSpi {

    Hero create(Hero hero);

    List<Hero> findAllHeroes();
}