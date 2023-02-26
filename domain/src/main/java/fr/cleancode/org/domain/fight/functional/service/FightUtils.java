package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.domain.player.functional.model.Player;

import java.util.ArrayList;

public interface FightUtils {

    default int calculateMatchupBuff(Hero attacker, Hero defender) {
        if (attacker.getSpeciality() == Speciality.TANK && defender.getSpeciality() == Speciality.MAGE) {
            return 20;
        } else if (attacker.getSpeciality() == Speciality.ASSASSIN && defender.getSpeciality() == Speciality.TANK) {
            return 30;
        } else if (attacker.getSpeciality() == Speciality.MAGE && defender.getSpeciality() == Speciality.ASSASSIN) {
            return 25;
        } else {
            return 0;
        }
    }

    default void attack(Hero attacker, Hero defender) {
        defender.setHealthPoints(defender.getHealthPoints() - (attacker.getPower() - defender.getArmor()));
    }

    default void updateHeroInDeck(Player player, Hero updatedHero) {
        ArrayList<Hero> deck = player.getDeck();
        int index = -1;
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i).getHeroId().equals(updatedHero.getHeroId())) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            deck.set(index, updatedHero);
            player.setDeck(deck);
        }
    }

}
