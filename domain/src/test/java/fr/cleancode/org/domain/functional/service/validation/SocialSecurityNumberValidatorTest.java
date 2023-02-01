package fr.cleancode.org.domain.functional.service.validation;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.DrivingLicence;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class SocialSecurityNumberValidatorTest {

  @ParameterizedTest
  @ValueSource(strings = {"123456789012345", "098765432109876"})
  void should_validate(String validSSNumber) {
    val actual = SocialSecurityNumberValidator.validate(DrivingLicence.builder().driverSSNumber(validSSNumber).build());
    assertThat(actual).containsValidInstanceOf(DrivingLicence.class);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"lorem ipsum", "azertyuiopmlkjh", "09876543210987654321", "098654"})
  void should_not_validate(String invalidSSNumber) {
    val actual = SocialSecurityNumberValidator.validate(DrivingLicence.builder().driverSSNumber(invalidSSNumber).build());
    assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
  }
}
