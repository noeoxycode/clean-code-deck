package fr.cleancode.org.domain.player.ports.server;

import fr.cleancode.org.domain.player.functional.model.Player;

public interface PlayerCreatorSpi {

    Player save(Player player);
}