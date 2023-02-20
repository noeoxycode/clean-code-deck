package fr.cleancode.org.domain.fight.port.client;

import fr.cleancode.org.domain.fight.functional.model.Fight;

import java.util.UUID;

public interface FightApi {
    Fight fight(Fight fight, UUID attackerId);
}