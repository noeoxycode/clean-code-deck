package fr.cleancode.org.domain.ports.client;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import io.vavr.control.Either;

public interface HeroCreatorApi {
    Either<ApplicationError, Hero> create(Hero hero);
}
