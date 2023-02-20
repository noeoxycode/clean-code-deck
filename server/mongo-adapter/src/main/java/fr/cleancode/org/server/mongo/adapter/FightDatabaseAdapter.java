package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.server.mongo.mapper.FightEntityMapper;
import fr.cleancode.org.server.mongo.repository.FightRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FightDatabaseAdapter implements FightFinderSpi, FightCreatorSpi {

    private final FightRepository fightRepository;

    @Override
    public Fight create(Fight fight) {
        val entity = FightEntityMapper.fromDomain(fight);
        fightRepository.save(entity);
        return FightEntityMapper.toDomain(entity);
    }

    @Override
    public Fight findFightById(UUID fightId) {
        return FightEntityMapper
                .toDomain(fightRepository
                        .findFightEntitiesByFightId(fightId));
    }

    @Override
    public List<Fight> findAllFights() {
        return FightEntityMapper.toDomainList(fightRepository.findAll());
    }
}