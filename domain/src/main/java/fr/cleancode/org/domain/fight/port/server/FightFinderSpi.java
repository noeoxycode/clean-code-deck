package fr.cleancode.org.domain.fight.port.server;

import fr.cleancode.org.domain.fight.functional.model.Fight;

import java.util.List;

public interface FightFinderSpi {

    List<Fight> findAllFights();
}