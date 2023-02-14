package fr.cleancode.org.domain.player.functional.service.validation;

import fr.cleancode.org.domain.player.functional.model.Player;


public interface PlayerValidator {

    static boolean validate(Player player) {
        return player.getPseudo() != null;
    }
}