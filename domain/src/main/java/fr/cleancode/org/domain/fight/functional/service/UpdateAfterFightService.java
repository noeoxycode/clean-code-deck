package fr.cleancode.org.domain.fight.functional.service;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.money.EarningTokenService;
import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class UpdateAfterFightService implements FightUtils {
    private final EarningTokenService earningTokenService;

    public Player updatePlayerAndHeroAfterFightWon(Player player, Fight fight, Hero attacker, UUID winner) {
        if (winner.equals(attacker.getHeroId())) {
            attacker = updateHeroStatisticsAfterWin(attacker);
            updateHeroInDeck(player, attacker);
            ArrayList<UUID> newHistoFights = new ArrayList<>();
            if (player.getFights() != null) {
                newHistoFights = new ArrayList<>(player.getFights());
                newHistoFights.add(fight.getFightId());
                player.setFights(newHistoFights);
            } else {
                newHistoFights.add(fight.getFightId());
                player.setFights(newHistoFights);
            }
            player = earningTokenService.earningToken(player);
        }
        return player;
    }


}
