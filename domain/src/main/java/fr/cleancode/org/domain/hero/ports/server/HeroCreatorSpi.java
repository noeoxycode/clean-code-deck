package fr.cleancode.org.domain.hero.ports.server;

import fr.cleancode.org.domain.hero.functional.model.Hero;

public interface HeroCreatorSpi {

    Hero create(Hero hero);
}