package fr.cleancode.org.domain.ports.client;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import io.vavr.control.Either;

import java.util.UUID;

public interface HeroFinderApi {
    Either<ApplicationError, Hero> findHeroById(UUID heroId);
}
