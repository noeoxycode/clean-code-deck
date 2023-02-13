package fr.cleancode.org.domain.player.functional.service;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.functional.service.validation.PlayerValidator;
import fr.cleancode.org.domain.player.ports.client.PlayerCreatorApi;
import fr.cleancode.org.domain.player.ports.server.PlayerPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlayerCreatorService implements PlayerCreatorApi {

    private final PlayerPersistenceSpi spi;

    @Override
    public Either<ApplicationError, Player> create(Player player) {
        return PlayerValidator.validate(player)
                .toEither()
                .peekLeft(
                        error -> log.error("An error occurred while validating player : {}", error))
                .flatMap(spi::save);
    }
}