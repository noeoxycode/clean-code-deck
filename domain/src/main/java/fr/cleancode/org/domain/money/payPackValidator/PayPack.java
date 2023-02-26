package fr.cleancode.org.domain.money.payPackValidator;

import fr.cleancode.org.domain.fight.functional.service.validation.FightValidator;
import fr.cleancode.org.domain.pack.functional.exception.PaymentException;
import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

import static fr.cleancode.org.domain.money.validation.PaymentValidator.validate;

public interface PayPack {

    Logger logger
            = Logger.getLogger(
            FightValidator.class.getName());

    static boolean payPack(Player player, int cost) {
        if(player == null){
            logger.log(Level.SEVERE,"Invalid payment transaction");
            return false;
        }
        if (!validate(player, cost)) {
            logger.log(Level.SEVERE,"Invalid payment transaction");
            return false;
        }
        player.setToken(player.getToken() - cost);
        return true;
    }
}