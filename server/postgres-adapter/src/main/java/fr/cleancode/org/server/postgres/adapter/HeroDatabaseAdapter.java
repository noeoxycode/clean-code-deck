package fr.cleancode.org.server.postgres.adapter;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.ports.server.HeroPersistenceSpi;
import fr.cleancode.org.server.postgres.mapper.HeroEntityMapper;
import fr.cleancode.org.server.postgres.repository.HeroRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class HeroDatabaseAdapter implements HeroPersistenceSpi {

    private final HeroRepository heroRepository;

    @Override
    @Transactional
    public Either<ApplicationError, Hero> save(Hero hero) {
        val entity = HeroEntityMapper.fromDomain(hero);
        return Try(() -> heroRepository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save hero", null, hero, throwable))
                .map(HeroEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Hero findById(UUID heroId) {
        return HeroEntityMapper
                .toDomain(heroRepository.findHeroEntityByHeroId(heroId));
    }
}