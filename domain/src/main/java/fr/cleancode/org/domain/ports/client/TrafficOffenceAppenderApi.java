package fr.cleancode.org.domain.ports.client;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.DrivingLicence;
import fr.cleancode.org.domain.functional.model.TrafficOffence;
import io.vavr.control.Either;

import java.util.UUID;

public interface TrafficOffenceAppenderApi {
  Either<ApplicationError, DrivingLicence> appendOffence(
      UUID drivingLicenceId, TrafficOffence offence);
}
