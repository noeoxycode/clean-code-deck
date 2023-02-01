package fr.cleancode.org.domain.ports.server;

import fr.cleancode.org.domain.ApplicationError;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface PersistenceSpi<T, ID> {
  Either<ApplicationError, T> save(T o);

  Option<T> findById(ID id);
}
