package fr.cleancode.org.domain.hero.ports.client;

import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;

public interface HeroFinderApi {
    List<Hero> findAllHeroes();

    List<Hero> findAllCarts();
}