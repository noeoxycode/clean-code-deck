package fr.cleancode.org.domain.ports.server;

import fr.cleancode.org.domain.functional.model.hero.Hero;

import java.util.UUID;

public interface HeroPersistenceSpi extends PersistenceSpi<Hero, UUID> {
}