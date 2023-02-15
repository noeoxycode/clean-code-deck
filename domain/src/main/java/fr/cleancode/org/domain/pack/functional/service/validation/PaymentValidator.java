package fr.cleancode.org.domain.pack.functional.service.validation;

import fr.cleancode.org.domain.player.functional.model.Player;

public interface PaymentValidator {
    static Boolean validate(Player player, int price){
        return player.getToken() >= price;
    };
}
