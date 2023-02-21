package fr.cleancode.org.domain.pack.ports.client;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.functional.model.PackType;

import java.util.List;
import java.util.UUID;

public interface OpenPackApi {
    List<Hero> openPack(UUID player, PackType packType);
}
