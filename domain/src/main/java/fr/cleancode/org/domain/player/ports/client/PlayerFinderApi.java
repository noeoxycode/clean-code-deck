package fr.cleancode.org.domain.player.ports.client;

import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.UUID;

public interface PlayerFinderApi {

    Player findPlayerById(UUID playerId);
}