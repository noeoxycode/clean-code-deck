package fr.cleancode.org.domain.player.functional.service;

import fr.cleancode.org.domain.player.functional.exception.PlayerException;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.functional.service.validation.PlayerValidator;
import fr.cleancode.org.domain.player.ports.server.PlayerUpdateSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PlayerUpdateService {

    private final PlayerUpdateSpi spi;

    public Player update(Player player) {
        if (!PlayerValidator.validate(player)) {
            throw new PlayerException("Unable to validate the player");
        }
        return spi.update(player);
    }
}