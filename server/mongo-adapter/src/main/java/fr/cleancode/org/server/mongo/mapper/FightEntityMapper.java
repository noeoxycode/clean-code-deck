package fr.cleancode.org.server.mongo.mapper;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.server.mongo.entities.FightEntity;
import fr.cleancode.org.server.mongo.entities.PlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface FightEntityMapper {

    static Fight toDomain(FightEntity entity) {
        return Fight.builder()
                .fightId(entity.getFightId())
                .date(entity.getDate())
                .attacker(entity.getAttacker())
                .defender(entity.getDefender())
                .winner(entity.getWinner())
                .build();
    }

    static FightEntity fromDomain(Fight domain) {
        return FightEntity.builder()
                .fightId(domain.getFightId())
                .date(domain.getDate())
                .attacker(domain.getAttacker())
                .defender(domain.getDefender())
                .winner(domain.getWinner())
                .build();
    }

    static List<Fight> toDomainList(List<FightEntity> entityList) {
        return entityList.stream()
                .map(entity -> toDomain(entity))
                .collect(Collectors.toList());
    }
}