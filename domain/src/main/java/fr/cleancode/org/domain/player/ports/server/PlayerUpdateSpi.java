package fr.cleancode.org.domain.player.ports.server;

import fr.cleancode.org.domain.player.functional.model.Player;

public interface PlayerUpdateSpi {

    Player update(Player player);
}