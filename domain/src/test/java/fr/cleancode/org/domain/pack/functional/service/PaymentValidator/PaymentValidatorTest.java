package fr.cleancode.org.domain.pack.functional.service.PaymentValidator;

import fr.cleancode.org.domain.money.validation.PaymentValidator;
import fr.cleancode.org.domain.player.functional.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentValidatorTest {

    @Test
    public void should_validate_player_when_he_has_enough_tokens() {
        Player player = Player.builder().token(10).build();
        int price = 5;
        boolean result = PaymentValidator.validate(player, price);
        assertTrue(result);
    }

    @Test
    public void should_not_validate_player_when_does_not_have_enough_tokens() {
        Player player = Player.builder().token(4).build();
        int price = 5;
        boolean result = PaymentValidator.validate(player, price);
        assertFalse(result);
    }

    @Test
    public void should_validate_player_when_he_has_exact_tokens_quantity() {
        Player player = Player.builder().token(5).build();
        int price = 5;
        boolean result = PaymentValidator.validate(player, price);
        assertTrue(result);
    }
}
