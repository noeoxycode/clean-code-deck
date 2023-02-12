package fr.cleancode.org.domain.ports.client;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import io.vavr.control.Either;

import java.util.UUID;

public interface HeroFinderApi {
    Hero findHeroById(UUID heroId);
}
