package fr.cleancode.org.domain.money.payPackValidator;

import fr.cleancode.org.domain.pack.functional.exception.PaymentException;
import fr.cleancode.org.domain.player.functional.model.Player;

import static fr.cleancode.org.domain.money.validation.PaymentValidator.validate;

public interface PayPack {
    static void payPack(Player player, int cost) {
        if (!validate(player, cost)) {
            throw new PaymentException("Cannot open pack due to insufficient token amount");
        }
        player.setToken(player.getToken() - cost);
    }
}