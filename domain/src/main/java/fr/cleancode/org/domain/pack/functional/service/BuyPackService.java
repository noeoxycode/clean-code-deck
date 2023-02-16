package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.functional.exception.PaymentException;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.ports.client.BuyPackApi;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class BuyPackService implements Buying, BuyPackApi {

    private final PlayerPersistenceSpi spi;

    private final PackContentGeneratorService packContentGeneratorService;
    public List<Hero> buyPack(UUID playerId, Pack pack) {
        Player player = spi.findPlayerById(playerId);
        payProduct(player, pack.getCost());
        List<Hero> packContent = packContentGeneratorService.generateContent(pack);
        player.getDeck().addAll(packContent);
        spi.save(player);
        return packContent;
    }
}
