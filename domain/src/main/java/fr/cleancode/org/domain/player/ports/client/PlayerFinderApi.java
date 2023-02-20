package fr.cleancode.org.domain.player.ports.client;

import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerFinderApi {

    Optional<Player> findPlayerById(UUID playerId);
}