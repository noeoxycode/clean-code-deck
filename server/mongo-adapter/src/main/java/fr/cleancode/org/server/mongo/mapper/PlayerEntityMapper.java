package fr.cleancode.org.server.mongo.mapper;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.server.mongo.entities.PlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface PlayerEntityMapper {

    static Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .playerId(entity.getPlayerId())
                .pseudo(entity.getPseudo())
                .token(entity.getToken())
                .deck(entity.getDeck())
                .fights(entity.getFight())
                .build();
    }

    static PlayerEntity fromDomain(Player domain) {
        return PlayerEntity.builder()
                .playerId(domain.getPlayerId())
                .pseudo(domain.getPseudo())
                .token(domain.getToken())
                .deck(domain.getDeck())
                .fight(domain.getFights())
                .build();
    }

    static List<Player> toDomainList(List<PlayerEntity> entityList) {
        return entityList.stream()
                .map(entity -> toDomain(entity))
                .collect(Collectors.toList());
    }
}