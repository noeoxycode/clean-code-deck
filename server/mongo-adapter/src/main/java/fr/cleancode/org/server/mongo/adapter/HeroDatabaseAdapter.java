package fr.cleancode.org.server.postgres.adapter;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.ports.server.HeroPersistenceSpi;
import fr.cleancode.org.server.postgres.mapper.HeroEntityMapper;
import fr.cleancode.org.server.postgres.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroDatabaseAdapter implements HeroPersistenceSpi {

    private final HeroRepository heroRepository;

    @Override
    @Transactional
    public Hero save(Hero hero) {
        val entity = HeroEntityMapper.fromDomain(hero);
        heroRepository.save(entity);
        return HeroEntityMapper.toDomain(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Hero findById(UUID heroId) {
        return HeroEntityMapper
                .toDomain(heroRepository.findHeroEntityByHeroId(heroId));
    }
}