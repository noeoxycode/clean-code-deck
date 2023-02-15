package fr.cleancode.org.domain.player.functional.service.validation;

import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerValidatorTest {

    @Test
    void should_validate() {
        val given = Player.builder()
                .pseudo("sacha")
                .build();
        val actual = PlayerValidator.validate(given);
        assertThat(actual).isInstanceOf(Player.class);
    }

//    @Test
//    void should_not_validate() {
//        val actual = PlayerValidator.validate(Player.builder().build());
//        assertThat(actual).isInstanceOf(ApplicationError.class);
//    }
}