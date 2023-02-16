package fr.cleancode.org.domain.pack.fonctional.service.validation;

import fr.cleancode.org.domain.pack.functional.service.validation.PaymentValidator;
import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaymentValidatorTest {

    @Test
    void shouldValidate() {
        val player = Player.builder().build();
        int cost = 1;

        val isValid = PaymentValidator.validate(player, cost);

        assertThat(isValid).isTrue();
    }

    @Test
    void shouldNotValidate() {
        val player = Player.builder().build();
        player.setToken(0);
        int cost = 1;

        val isValid = PaymentValidator.validate(player, cost);

        assertThat(isValid).isFalse();
    }

}
