package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.exception.FightException;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.functional.service.validation.FightValidator;
import fr.cleancode.org.domain.fight.port.client.Fightapi;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.client.PlayerFinderApi;
import fr.cleancode.org.domain.player.ports.server.PlayerUpdateSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FightService implements Fightapi {

    private final HeroFinderApi heroFinderApi;

    private final PlayerFinderApi playerFinderApi;

    private final PlayerUpdateSpi spi;

    public Fight fight(Fight fight, UUID attackerId) {
    Player player = playerFinderApi.findPlayerById(attackerId);
    Hero attacker = null;
    Hero defender = null;
    List<Hero> listHeroes = heroFinderApi.findAllCarts();
    for(Hero hero : listHeroes){
        if(hero.getHeroId().equals(fight.getAttacker()) ){
            attacker = hero;
        }
        if(hero.getHeroId().equals(fight.getDefender())){
            defender = hero;
        }
    }
    if(attacker == null || defender == null){
        throw new FightException("Hero not found");
    }
    FightValidator.fightParametersChecking(player, fight, attacker, defender);
    fight.setWinner(figthing(attacker, defender));
    if(fight.getWinner().equals(fight.getAttacker())){
        updateXp(attacker);
        List<Hero> newDeck = new ArrayList<>(player.getDeck());
        newDeck.removeIf(hero -> hero.getHeroId().equals(attackerId));
        newDeck.add(attacker);
        player.setDeck(newDeck);

        player.getDeck().add(attacker);
    }
    if(FightValidator.validate(fight)){
        List<UUID> newHistoFights = new ArrayList<>(player.getFights());
        newHistoFights.add(fight.getFightId());
        player.setFights(newHistoFights);
        spi.update(player);
        return fight;
    }
    else throw new FightException("Error while fighting");
    }

    public UUID figthing(Hero attacker, Hero defender){
        int turn = 0;
        attacker.setPower(attacker.getPower() + matchupBuff(attacker, defender));
        defender.setPower(attacker.getPower() + matchupBuff(defender, attacker));

        while (attacker.getHealthPoints() > 0 && defender.getHealthPoints() > 0) {
            if (turn % 2 == 0) {
                attack(attacker, defender);
            } else {
                attack(defender, attacker);
            }
            turn++;
        }
        if(attacker.getHealthPoints() <= 0){
            return defender.getHeroId();
        }
        else {
            return attacker.getHeroId();
        }
    }

    int matchupBuff(Hero attacker, Hero defender) {
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

    void attack(Hero attacker, Hero defender){
        defender.setHealthPoints(
                defender.getHealthPoints() -
                        (attacker.getPower() - defender.getArmor())
        );
    }

    void updateXp(Hero hero){
        if(hero.getCurrentExperiences() == 4){
            hero.setCurrentExperiences(0);
            hero.setLevel(hero.getLevel()+1);
        }
        else {
            hero.setCurrentExperiences(hero.getCurrentExperiences()+1);
        }
    }


}