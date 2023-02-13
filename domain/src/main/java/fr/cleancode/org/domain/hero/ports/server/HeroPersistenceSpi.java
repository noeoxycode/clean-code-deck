package fr.cleancode.org.domain.hero.ports.server;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import io.vavr.control.Either;

import java.util.UUID;

public interface HeroPersistenceSpi {

    Either<ApplicationError, Hero> save(Hero hero);

    Hero findById(UUID heroId);
}