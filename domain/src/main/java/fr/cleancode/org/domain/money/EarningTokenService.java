package fr.cleancode.org.domain.money;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class EarningTokenService {

    private final FightFinderSpi fightFinderSpi;

    public void earningToken(Player player) {
        List<Fight> fightHistory = fightFinderSpi.findAllFights();
        List<UUID> playersHeroesId = new ArrayList<>();
        for (Hero hero : player.getDeck()) {
            playersHeroesId.add(hero.getHeroId());
        }
        long count = fightHistory.stream()
                .filter(fight -> playersHeroesId.contains(fight.getWinner()))
                .count();
        if (count % 5 == 0 && count != 0) {
            player.setToken(player.getToken() + 1);
        }
    }

}
