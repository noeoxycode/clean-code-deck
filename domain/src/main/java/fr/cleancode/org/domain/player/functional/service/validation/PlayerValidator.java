package fr.cleancode.org.domain.player.functional.service.validation;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.player.functional.model.Player;
import io.vavr.control.Validation;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;

public interface PlayerValidator {

    static Validation<ApplicationError, Player> validate(Player player) {
        return player.getPseudo() != null
                ? Valid(player)
                : Invalid(new ApplicationError("Invalid field with the player {}", null, player, null));
    }
}