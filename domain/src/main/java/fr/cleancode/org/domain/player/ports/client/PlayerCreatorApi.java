package fr.cleancode.org.domain.player.ports.client;

import fr.cleancode.org.domain.player.functional.model.Player;

public interface PlayerCreatorApi {

    Player create(Player player);

}