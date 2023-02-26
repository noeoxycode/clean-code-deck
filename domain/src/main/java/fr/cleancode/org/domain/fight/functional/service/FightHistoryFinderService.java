package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.functional.service.validation.FightHistoryValidator;
import fr.cleancode.org.domain.fight.port.client.FightFinderApi;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FightHistoryFinderService implements FightFinderApi, FightHistoryValidator {

    private final FightFinderSpi spi;

    public Optional<List<Fight>> findFightsHistory(UUID heroId) {
        Optional<List<Fight>> heroFightHistory = spi.findHeroFightsHistory(heroId);
        if (!validate(heroFightHistory.get())){
            return Optional.empty();
        }
        else return heroFightHistory;
    }

}
