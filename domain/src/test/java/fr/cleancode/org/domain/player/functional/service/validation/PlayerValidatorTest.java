package fr.cleancode.org.domain.player.functional.service.validation;

import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerValidatorTest {

    @Test
    void should_validate() {
        val given = Player.builder()
                .playerId(UUID.randomUUID())
                .pseudo("sacha")
                .deck(new ArrayList<>())
                .build();

        val actual = PlayerValidator.validate(given);

        assertThat(actual).isTrue();
    }

    @Test
    void should_not_validate() {
        val given = Player.builder()
                .playerId(null)
                .pseudo("sacha")
                .deck(null)
                .build();

        val actual = PlayerValidator.validate(given);

        assertThat(actual).isFalse();
    }
}