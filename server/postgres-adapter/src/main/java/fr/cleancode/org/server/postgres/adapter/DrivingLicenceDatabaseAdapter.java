package fr.cleancode.org.server.postgres.adapter;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.DrivingLicence;
import fr.cleancode.org.domain.ports.server.DrivingLicencePersistenceSpi;
import fr.cleancode.org.server.postgres.mapper.DrivingLicenceEntityMapper;
import fr.cleancode.org.server.postgres.repository.DrivingLicenceRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class DrivingLicenceDatabaseAdapter implements DrivingLicencePersistenceSpi {

  private final DrivingLicenceRepository repository;

  @Override
  @Transactional
  public Either<ApplicationError, DrivingLicence> save(DrivingLicence o) {
    val entity = DrivingLicenceEntityMapper.fromDomain(o);
    return Try(() -> repository.save(entity))
        .toEither()
        .mapLeft(throwable -> new ApplicationError("Unable to save licence", null, o, throwable))
        .map(DrivingLicenceEntityMapper::toDomain);
  }

  @Override
  @Transactional(readOnly = true)
  public Option<DrivingLicence> findById(UUID id) {
    return repository.findDrivingLicenceEntityById(id).map(DrivingLicenceEntityMapper::toDomain);
  }
}
