package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.exception.FightException;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.functional.service.validation.FightValidator;
import fr.cleancode.org.domain.fight.port.client.Fightapi;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
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

    private final PlayerUpdateSpi playerUpdateSpi;

    private final FightCreatorSpi fightCreatorSpi;

    public Fight fight(Fight fight, UUID idPlayer) {
    Player player = playerFinderApi.findPlayerById(idPlayer);
    Hero attacker = null;
    Hero defender = null;
    List<Hero> listHeroes = heroFinderApi.findAllCarts();
    for(Hero hero : listHeroes){
        if(hero.getHeroId().equals(fight.getAttacker()) ){
            attacker = hero;
            System.out.println("id de attacker dans le for");
            System.out.println(attacker.getHeroId());
        }
        if(hero.getHeroId().equals(fight.getDefender())){
            defender = hero;
        }
    }
    if(attacker == null || defender == null){
        throw new FightException("Hero not found");
    }
    FightValidator.fightParametersChecking(player, fight, attacker, defender);
        System.out.println("id attacker apres figtValidator");
        System.out.println(attacker.getHeroId());
    UUID winner = figthing(attacker, defender);
        System.out.println("id attacker apres fighting");
        System.out.println(attacker.getHeroId());
    fight.setWinner(winner);
    if (fight.getWinner().equals(fight.getAttacker())) {
        updateXp(attacker);
        System.out.println("id attacker apres updatexp");
        System.out.println(attacker.getHeroId());
        List<Hero> deck = player.getDeck();
        int index = -1;
        for (int i = 0; i < deck.size(); i++) {
            System.out.println("hero du deck");
            System.out.println(deck.get(i).getHeroId());
            System.out.println("hero id a comparer avec");
            System.out.println(attacker.getHeroId());
            if (deck.get(i).getHeroId().equals(attacker.getHeroId())) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            deck.set(index, attacker);
            player.setDeck(deck);
        }
    }
    if(FightValidator.validate(fight)){
        List<UUID> newHistoFights = new ArrayList<>();
        if(player.getFights() != null){
            newHistoFights.addAll(player.getFights());
        }
        newHistoFights.add(fight.getFightId());
        player.setFights(newHistoFights);
        playerUpdateSpi.update(player);
        fightCreatorSpi.create(fight);
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