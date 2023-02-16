package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.pack.functional.exception.PaymentException;
import fr.cleancode.org.domain.pack.functional.service.validation.PaymentValidator;
import fr.cleancode.org.domain.player.functional.model.Player;

import java.text.ParseException;

public interface Buying {
    default void payProduct(Player player, int price) throws PaymentException{
        if(PaymentValidator.validate(player, price)){
            player.setToken(player.getToken()-price);
        }
        else {
            throw  new PaymentException("Payment invalidated");
        }
    }
}