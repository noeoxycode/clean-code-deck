package fr.cleancode.org.domain.functional.service;

import fr.cleancode.org.domain.functional.model.hero.Hero;
import fr.cleancode.org.domain.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.ports.server.HeroPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class HeroFinderService implements HeroFinderApi {

    private final HeroPersistenceSpi spi;

    public Hero findHeroById(UUID heroId) {
        return spi.findById(heroId);
    }
}
