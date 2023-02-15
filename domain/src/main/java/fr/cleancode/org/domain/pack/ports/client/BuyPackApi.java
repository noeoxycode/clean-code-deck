package fr.cleancode.org.domain.pack.ports.client;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.functional.model.Pack;

import java.util.List;
import java.util.UUID;

public interface BuyPackApi {
    List<Hero> buyPack(UUID player, Pack pack);
}
