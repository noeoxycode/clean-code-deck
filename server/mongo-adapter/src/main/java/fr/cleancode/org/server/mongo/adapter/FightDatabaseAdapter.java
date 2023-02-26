package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.fight.functional.exception.FightException;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.server.mongo.entities.FightEntity;
import fr.cleancode.org.server.mongo.mapper.FightEntityMapper;
import fr.cleancode.org.server.mongo.repository.FightRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FightDatabaseAdapter implements FightFinderSpi, FightCreatorSpi {

    private final FightRepository fightRepository;

    @Override
    public Fight save(Fight fight) {
        val entity = FightEntityMapper.fromDomain(fight);
        fightRepository.save(entity);
        return FightEntityMapper.toDomain(entity);
    }

    @Override
    public List<Fight> findAllFights() {
        return FightEntityMapper.toDomainList(fightRepository.findAll());
    }

    @Override
    public Optional<List<Fight>> findHeroFightsHistory(UUID heroId) {
        Optional<List<FightEntity>> fights = Optional.of(fightRepository.findAll());
        return fights.map(FightEntityMapper::toDomainList)
                .map(fightList -> fightList.stream()
                        .filter(fight -> fight.getAttacker().equals(heroId) || fight.getDefender().equals(heroId))
                        .collect(Collectors.toList()))
                .map(Optional::ofNullable)
                .orElseThrow(() -> new FightException("Hero with id : " + heroId + " was not found !"));
    }


}