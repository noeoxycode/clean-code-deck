package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class HeroFinderService implements HeroFinderApi {

    private final HeroFinderSpi spi;

    public List<Hero> findAllHeroes() {
        return spi.findAllHeroes();
    }
}