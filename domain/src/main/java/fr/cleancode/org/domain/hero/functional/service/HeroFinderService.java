package fr.cleancode.org.domain.hero.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class HeroFinderService implements HeroFinderApi {

    private final HeroFinderSpi spi;

    private final PlayerFinderSpi playerFinderSpi;

    public List<Hero> findAllHeroes() {
        return spi.findAllHeroes();
    }

    public List<Hero> findAllOwnedHeroes() {
        List<Player> listPlayers = playerFinderSpi.findAllPlayers();
        List<Hero> heroesList = new ArrayList<>();
        for (Player player : listPlayers) {
            heroesList.addAll(player.getDeck());
        }
        return heroesList;
    }

    public Optional<Hero> findHeroById(UUID heroId) {
        List<Hero> listHeroes = findAllOwnedHeroes();
        for (Hero hero : listHeroes) {
            if (hero.getHeroId().equals(heroId)) {
                return Optional.of(hero);
            }
        }
        return Optional.empty();
    }

}