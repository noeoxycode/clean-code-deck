package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.ports.server.HeroCreatorSpi;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import fr.cleancode.org.server.mongo.mapper.HeroEntityMapper;
import fr.cleancode.org.server.mongo.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

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

}