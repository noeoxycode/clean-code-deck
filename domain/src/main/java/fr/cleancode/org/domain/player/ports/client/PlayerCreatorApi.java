package fr.cleancode.org.domain.player.ports.client;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.player.functional.model.Player;
import io.vavr.control.Either;

public interface PlayerCreatorApi {

    Either<ApplicationError, Player> create(Player player);

}