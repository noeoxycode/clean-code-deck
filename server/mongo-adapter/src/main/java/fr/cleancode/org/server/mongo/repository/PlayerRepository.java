package fr.cleancode.org.server.mongo.repository;

import fr.cleancode.org.server.mongo.entities.PlayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository extends MongoRepository<PlayerEntity, UUID> {

    PlayerEntity findPlayerEntitiesByPlayerId(UUID playerId);
}