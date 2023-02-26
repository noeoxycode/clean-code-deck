package fr.cleancode.org.domain.money.payPackValidator;

import fr.cleancode.org.domain.fight.functional.service.validation.FightValidator;
import fr.cleancode.org.domain.pack.functional.exception.PaymentException;
import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

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