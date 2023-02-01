package fr.cleancode.org.domain.functional.service.validation;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.TrafficOffence;
import io.vavr.control.Validation;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;

public interface TrafficOffenceValidator {

  static Validation<ApplicationError, TrafficOffence> validate(TrafficOffence offence) {
    return offence == null || offence.getLabel() == null || offence.getLabel().isBlank()
        ? Invalid(
            new ApplicationError(
                "Label required", "You must provide an offence label", offence, null))
        : Valid(offence);
  }
}
