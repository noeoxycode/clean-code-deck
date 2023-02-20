package fr.cleancode.org.server.mongo.repository;

import fr.cleancode.org.server.mongo.entities.FightEntity;
import fr.cleancode.org.server.mongo.entities.HeroEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FightRepository extends MongoRepository<FightEntity, UUID> {

    FightEntity findFightEntitiesByFightId(UUID fightId);
}