package fr.cleancode.org.domain.fight.port.client;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FightFinderApi {

    Optional<List<Fight>> findFightsHistory(UUID heroId);


}
