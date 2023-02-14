package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.service.validation.HeroValidator;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.hero.ports.server.HeroPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static fr.cleancode.org.domain.hero.functional.service.initialization.HeroInitializer.initializeHeroPropertiesByRarity;
import static fr.cleancode.org.domain.hero.functional.service.initialization.HeroInitializer.initializeHeroPropertiesBySpeciality;

@Slf4j
@RequiredArgsConstructor
public class HeroCreatorService implements HeroCreatorApi {

    private final HeroPersistenceSpi spi;

    @Override
    public Hero create(Hero hero) {
        initializeHeroPropertiesBySpeciality(hero);
        initializeHeroPropertiesByRarity(hero);
        HeroValidator.validate(hero);
        return spi.save(hero);
    }
}