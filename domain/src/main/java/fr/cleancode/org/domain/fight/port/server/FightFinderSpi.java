package fr.cleancode.org.domain.fight.port.server;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.List;
import java.util.UUID;

public interface FightFinderSpi {

    Fight findFightById(UUID fightId);

    List<Fight> findAllFights();
}