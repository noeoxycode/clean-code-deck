package fr.cleancode.org.domain.hero.ports.client;

import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HeroFinderApi {
    List<Hero> findAllHeroes();

    List<Hero> findAllCarts();

    Optional<Hero> findHeroById(UUID id);
}