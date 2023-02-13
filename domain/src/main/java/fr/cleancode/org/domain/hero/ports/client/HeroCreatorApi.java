package fr.cleancode.org.domain.hero.ports.client;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import io.vavr.control.Either;

public interface HeroCreatorApi {
    Either<ApplicationError, Hero> create(Hero hero);
}