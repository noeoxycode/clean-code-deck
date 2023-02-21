package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.hero.functional.exception.HeroException;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.ports.server.HeroCreatorSpi;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import fr.cleancode.org.server.mongo.entities.HeroEntity;
import fr.cleancode.org.server.mongo.mapper.HeroEntityMapper;
import fr.cleancode.org.server.mongo.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroDatabaseAdapter implements HeroCreatorSpi, HeroFinderSpi {

    private final HeroRepository heroRepository;

    @Override
    public Hero save(Hero hero) {
        val entity = HeroEntityMapper.fromDomain(hero);
        heroRepository.save(entity);
        return HeroEntityMapper.toDomain(entity);
    }

    @Override
    public List<Hero> findAllHeroes() {
        return HeroEntityMapper.toDomainList(heroRepository.findAll());
    }
/*
    @Override
    public Optional<Hero> findHeroById(UUID heroId) {
        Optional<HeroEntity> hero = heroRepository.findById(heroId);
        return Optional.ofNullable(hero
                .map(HeroEntityMapper::toDomain)
                .orElseThrow(
                        () -> new HeroException("Hero with id : "
                                + heroId + " was not found !"))
        );
    }
*/
}