package fr.cleancode.org.domain.ports.server;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import io.vavr.control.Either;

public interface PersistenceSpi<T, ID> {
    Either<ApplicationError, T> save(T o);

    Hero findById(ID heroId);
}