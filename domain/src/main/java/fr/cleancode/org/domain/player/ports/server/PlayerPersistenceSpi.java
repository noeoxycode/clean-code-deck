package fr.cleancode.org.domain.player.ports.server;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.player.functional.model.Player;
import io.vavr.control.Either;

public interface PlayerPersistenceSpi {
    Either<ApplicationError, Player> save(Player player);
}