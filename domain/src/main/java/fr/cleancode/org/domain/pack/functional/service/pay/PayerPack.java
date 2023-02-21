package fr.cleancode.org.domain.pack.functional.service.pay;

import fr.cleancode.org.domain.pack.functional.exception.PaymentException;
import fr.cleancode.org.domain.player.functional.model.Player;

import static fr.cleancode.org.domain.pack.functional.service.validation.PaymentValidator.validate;

public interface PayerPack {
    static void payPack(Player player, int cost) {
        if (!validate(player, cost)) {
            throw new PaymentException("Cannot open pack due to insufficient token amount");
        }
        player.setToken(player.getToken() - cost);
    }
}