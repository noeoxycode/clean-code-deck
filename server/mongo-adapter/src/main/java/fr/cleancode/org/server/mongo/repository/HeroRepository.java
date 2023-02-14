package fr.cleancode.org.server.mongo.repository;

import fr.cleancode.org.server.mongo.entities.HeroEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HeroRepository extends MongoRepository<HeroEntity, UUID> {
}