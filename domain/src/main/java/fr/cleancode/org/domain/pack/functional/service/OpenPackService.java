package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;
import fr.cleancode.org.domain.pack.ports.client.OpenPackApi;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import static fr.cleancode.org.domain.pack.functional.service.generator.PackGenerator.generatePack;
import static fr.cleancode.org.domain.pack.functional.service.initialization.PackInitializer.initializePackPropertiesByType;
import static fr.cleancode.org.domain.pack.functional.service.pay.PayerPack.payPack;

@Slf4j
@RequiredArgsConstructor
public class OpenPackService implements OpenPackApi {

    private final PlayerFinderSpi playerFinderSpi;

    private final PlayerCreatorSpi playerCreatorSpi;

    private final PackContentService packContentService;

    public List<Hero> openPack(UUID playerId, PackType packType) {
        Player player = playerFinderSpi
                .findPlayerById(playerId)
                .get();
        Pack pack = generatePack(packType);
        initializePackPropertiesByType(pack);
        int cost = pack.getCost();

        payPack(player, cost);
        List<Hero> heroes = packContentService
                .appendHeroesOnDeck(player, pack);
        playerCreatorSpi.save(player);

        return heroes;
    }
}