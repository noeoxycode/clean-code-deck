package fr.cleancode.org.domain.player.functional.service;

import fr.cleancode.org.domain.player.functional.exception.PlayerException;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.functional.service.validation.PlayerValidator;
import fr.cleancode.org.domain.player.ports.client.PlayerCreatorApi;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlayerCreatorService implements PlayerCreatorApi {

    private final PlayerCreatorSpi spi;

    @Override
    public Player save(Player player) {
        if (!PlayerValidator.validate(player)) {
            throw new PlayerException("Unable to validate the player");
        }
        return spi.save(player);
    }
}