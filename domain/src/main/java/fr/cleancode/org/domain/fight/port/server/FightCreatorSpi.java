package fr.cleancode.org.domain.fight.port.server;

import fr.cleancode.org.domain.fight.functional.model.Fight;

public interface FightCreatorSpi {

    Fight save(Fight fight);
}