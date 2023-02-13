package fr.cleancode.org.domain.hero.ports.client;

import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.UUID;

public interface HeroFinderApi {
    Hero findHeroById(UUID heroId);
}