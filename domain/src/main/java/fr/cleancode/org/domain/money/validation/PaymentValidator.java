package fr.cleancode.org.domain.money.validation;

import fr.cleancode.org.domain.player.functional.model.Player;

public interface PaymentValidator {
    static boolean validate(Player player, int price) {
        return player.getToken() >= price;
    }
}