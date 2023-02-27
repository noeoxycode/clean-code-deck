package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.hero.functional.exception.HeroException;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.service.validation.HeroValidator;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.hero.ports.server.HeroCreatorSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static fr.cleancode.org.domain.hero.functional.service.initialization.HeroInitializer.initializeHeroPropertiesByRarity;
import static fr.cleancode.org.domain.hero.functional.service.initialization.HeroInitializer.initializeHeroPropertiesBySpeciality;

@Slf4j
@RequiredArgsConstructor
public class HeroCreatorService implements HeroCreatorApi {

    private final HeroCreatorSpi spi;

    @Override
    public Hero save(Hero hero) {
        initializeHeroPropertiesBySpeciality(hero);
        initializeHeroPropertiesByRarity(hero);
        if (!HeroValidator.validate(hero)) {
            throw new HeroException("Unable to validate the hero");
        }
        return spi.save(hero);
    }

    @Override
    public List<Hero> saveAll(List<Hero> heroes) {
        ArrayList<Hero> heroesList = new ArrayList<>();
        if (!HeroValidator.validateList(heroes)) {
            throw new HeroException("Hero list is null");
        }
        for (Hero hero : heroes) {
            initializeHeroPropertiesBySpeciality(hero);
            initializeHeroPropertiesByRarity(hero);
            if (!HeroValidator.validate(hero)) {
                throw new HeroException("Unable to validate the hero");
            }
            heroesList.add(hero);
        }

        return spi.saveAll(heroesList);
    }
}
