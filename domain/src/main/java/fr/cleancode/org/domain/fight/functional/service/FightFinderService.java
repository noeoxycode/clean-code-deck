package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.client.FightFinderApi;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FightFinderService implements FightFinderApi {

    private final FightFinderSpi spi;

    public Optional<List<Fight>> findFightsHistory(UUID heroId) {
        Optional<List<Fight>> heroFightHistory = spi.findHeroFightsHistory(heroId);
        if (heroFightHistory.get().size() == 0){
            return Optional.empty();
        }
        else return heroFightHistory;
    }

}
