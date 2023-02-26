package fr.cleancode.org.domain.money.validation;

import fr.cleancode.org.domain.fight.functional.service.validation.FightValidator;
import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.logging.Level;
import java.util.logging.Logger;

public interface PaymentValidator {

    Logger logger
            = Logger.getLogger(
            FightValidator.class.getName());

    static boolean validate(Player player, int price) {
        if(price < 0){
            logger.log(Level.SEVERE,"Price cannot be negative");
            return false;
        }
        if(player == null){
            logger.log(Level.SEVERE,"Player is null");
            return false;
        }
        return player.getToken() >= price;
    }
}