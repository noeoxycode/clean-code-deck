package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.service.generator.PackContentGeneratorService;
import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class PackContentService {

    private final PackContentGeneratorService packContentGeneratorService;

    public List<Hero> appendHeroesOnDeck(Player player, Pack pack) {
        List<Hero> heroes = packContentGeneratorService
                .generateContent(pack);
        player.getDeck().addAll(heroes);
        return heroes;
    }
}