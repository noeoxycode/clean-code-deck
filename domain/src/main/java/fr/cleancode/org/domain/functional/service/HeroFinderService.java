package fr.cleancode.org.domain.functional.service;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import fr.cleancode.org.domain.functional.service.validation.HeroValidator;
import fr.cleancode.org.domain.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.ports.server.HeroPersistenceSpi;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import static io.vavr.API.Left;

@Slf4j
@RequiredArgsConstructor
public class HeroFinderService implements HeroFinderApi {

    private final HeroPersistenceSpi spi;

    public Either<ApplicationError, Hero> findHeroById(UUID heroId){
        return (Either<ApplicationError, Hero>) spi.findById(heroId)
                .onEmpty(() -> log.error("Unable to find driving licence with id {}", heroId))
                .fold(
                        () -> Left(new ApplicationError("No hero found", null, heroId, null)),
                        drivingLicence -> HeroValidator.validate((Hero) spi.findById(heroId)));
    }

}
