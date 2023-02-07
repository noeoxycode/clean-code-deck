package fr.cleancode.org.domain.ports.server;

import fr.cleancode.org.domain.ApplicationError;
import io.vavr.control.Either;

public interface PersistenceSpi<T, ID> {
    Either<ApplicationError, T> save(T o);
}