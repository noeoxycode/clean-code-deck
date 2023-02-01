package fr.cleancode.org.domain.ports.client;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.DrivingLicence;
import io.vavr.control.Either;

public interface DrivingLicenceCreatorApi {
  Either<ApplicationError, DrivingLicence> create(DrivingLicence drivingLicence);
}
