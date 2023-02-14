package fr.cleancode.org.server.mongo.mapper;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.server.mongo.entities.PlayerEntity;

public interface PlayerEntityMapper {

    static Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .playerId(entity.getPlayerId())
                .pseudo(entity.getPseudo())
                .token(entity.getToken())
                .deck(entity.getDeck())
                .build();
    }

    static PlayerEntity fromDomain(Player domain) {
        return PlayerEntity.builder()
                .playerId(domain.getPlayerId())
                .pseudo(domain.getPseudo())
                .token(domain.getToken())
                .deck(domain.getDeck())
                .build();
    }
}