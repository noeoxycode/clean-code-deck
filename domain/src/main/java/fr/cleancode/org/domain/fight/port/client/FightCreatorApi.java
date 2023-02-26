package fr.cleancode.org.domain.fight.port.client;

import fr.cleancode.org.domain.fight.functional.model.Fight;

import java.util.UUID;

public interface FightCreatorApi {
    Fight saveFifght(Fight fight, UUID attackerId);
}