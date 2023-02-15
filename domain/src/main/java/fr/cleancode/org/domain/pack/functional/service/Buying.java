package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.pack.functional.service.validation.PaymentValidator;
import fr.cleancode.org.domain.player.functional.model.Player;

public interface Buying {
    default int payProduct(Player player, int price){
        if(PaymentValidator.validate(player, price)){
            return spendMoney(player, price);
        }
        else {
            //exception handle
            return 99;
        }
    }

    private int spendMoney(Player user, int price){
        user.setToken(user.getToken()-price);
        return user.getToken();
    }
}