package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.service.validation.HeroValidator;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.hero.ports.server.HeroPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static fr.cleancode.org.domain.hero.functional.service.initialization.HeroInitializer.initializeHeroProperties;

@Slf4j
@RequiredArgsConstructor
public class HeroCreatorService implements HeroCreatorApi {

    private final HeroPersistenceSpi spi;

    @Override
    public Either<ApplicationError, Hero> create(Hero hero) {
        Hero specialHero = initializeHeroProperties(hero);
        return HeroValidator.validate(specialHero)
                .toEither()
                .peekLeft(
                        error -> log.error("An error occurred while validating hero : {}", error))
                .flatMap(spi::save);
    }
}