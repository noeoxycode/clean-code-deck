package fr.cleancode.org.server.mongo.mapper;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.server.mongo.entities.FightEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface FightEntityMapper {

    static Fight toDomain(FightEntity entity) {
        return Fight.builder()
                .fightId(entity.getFightId())
                .fightDate(entity.getDate())
                .attacker(entity.getAttacker())
                .defender(entity.getDefender())
                .winner(entity.getWinner())
                .build();
    }

    static FightEntity fromDomain(Fight domain) {
        return FightEntity.builder()
                .fightId(domain.getFightId())
                .date(domain.getFightDate())
                .attacker(domain.getAttacker())
                .defender(domain.getDefender())
                .winner(domain.getWinner())
                .build();
    }

    static List<Fight> toDomainList(List<FightEntity> entityList) {
        return entityList.stream()
                .map(FightEntityMapper::toDomain)
                .collect(Collectors.toList());
    }
}