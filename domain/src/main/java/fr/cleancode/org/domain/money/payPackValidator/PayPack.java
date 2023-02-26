package fr.cleancode.org.domain.money.payPackValidator;

import fr.cleancode.org.domain.player.functional.model.Player;

import static fr.cleancode.org.domain.money.validation.PaymentValidator.validate;

public interface PayPack {

    static boolean payPack(Player player, int cost) {
        if(player == null){
            return false;
        }
        if (!validate(player, cost)) {
            return false;
        }
        player.setToken(player.getToken() - cost);
        return true;
    }
}