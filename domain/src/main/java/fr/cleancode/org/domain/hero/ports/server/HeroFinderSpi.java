package fr.cleancode.org.domain.hero.ports.server;

import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HeroFinderSpi {

    List<Hero> findAllHeroes();

    Optional<Hero> findHeroById(UUID id);
}