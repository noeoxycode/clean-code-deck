package fr.cleancode.org.domain.functional.service.validation;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.TrafficOffence;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.vavr.api.VavrAssertions.assertThat;

class TrafficOffenceValidatorTest {

  @Test
  void should_validate() {
    val given = TrafficOffence.builder().label("label").build();
    val actual = TrafficOffenceValidator.validate(given);
    assertThat(actual).containsValidSame(given);
  }

  @ParameterizedTest
  @MethodSource("provideInvalidOffences")
  void should_not_validate(TrafficOffence invalidOffence) {
    val actual = TrafficOffenceValidator.validate(invalidOffence);
    assertThat(actual).containsInvalidInstanceOf(ApplicationError.class);
  }

  private static Stream<TrafficOffence> provideInvalidOffences() {
    return Stream.of(
        null,
        TrafficOffence.builder().build(),
        TrafficOffence.builder().label(null).build(),
        TrafficOffence.builder().label("").build());
  }
}
