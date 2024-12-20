package fr.cleancode.org.domain.player.functional.service;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.client.PlayerFinderApi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerFinderService implements PlayerFinderApi {

    private final PlayerFinderSpi spi;

    @Override
    public Optional<Player> findPlayerById(UUID playerId) {
        return spi.findPlayerById(playerId);
    }
}