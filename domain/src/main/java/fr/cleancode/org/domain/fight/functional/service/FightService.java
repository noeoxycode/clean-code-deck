package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.exception.FightException;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.functional.service.validation.FightValidator;
import fr.cleancode.org.domain.fight.port.client.FightCreatorApi;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.service.HeroFinderService;
import fr.cleancode.org.domain.player.functional.exception.PlayerException;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class FightService implements FightCreatorApi {
    private final HeroFinderService heroFinderService;

    private final PlayerFinderSpi playerFinderSpi;

    private final PlayerCreatorSpi playerCreatorSpi;

    private final FightCreatorSpi fightCreatorSpi;

    private final FightActionsService fightUtilsService;

    private final UpdateAfterFightService updateAfterFightService;


    public Fight saveFifght(Fight fight, UUID playerId) {
        Player player = playerFinderSpi.findPlayerById(playerId)
                .orElseThrow(() -> new PlayerException("Player not found"));
        Hero attacker = heroFinderService.findHeroById(fight.getAttacker())
                .orElseThrow(() -> new FightException("Attacker not found"));
        Hero defender = heroFinderService.findHeroById(fight.getDefender())
                .orElseThrow(() -> new FightException("Defender not found"));
        UUID winner = fightUtilsService.fightAction(attacker, defender);
        fight.setWinner(winner);
        if(!FightValidator.validate(player, fight, attacker, defender)){
            throw new FightException("Fight not valid");
        }
        player = updateAfterFightService.updatePlayerAndHeroAfterFightWon(player, fight, attacker, winner);

        playerCreatorSpi.save(player);
        fightCreatorSpi.save(fight);
        return fight;
    }

}
