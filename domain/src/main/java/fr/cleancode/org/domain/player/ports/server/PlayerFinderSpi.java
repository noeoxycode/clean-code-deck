package fr.cleancode.org.domain.player.ports.server;

import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.UUID;

public interface PlayerFinderSpi {

    Player findPlayerById(UUID playerId);
}