package fr.cleancode.org.domain.pack.functional.service.generator;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class PackContentGeneratorService {

    private final HeroFinderSpi heroFinderSpi;

    public static final Random RANDOM = new Random();

    public List<Hero> generateContent(Pack pack) {
        List<Hero> packContent = new ArrayList<>();
        List<Hero> heroes = heroFinderSpi.findAllHeroes();
        for (int i = 0; i < pack.getCardsQuantity(); i++) {
            packContent.add(getHeroFromPackProbability(heroes, pack.getProbability()));
        }
        return packContent;
    }

    private Hero getHeroFromPackProbability(List<Hero> heroes, int[] probabilities) {
        Rarity rarity = decideRarity(probabilities);
        List<Hero> heroesWithRarity = heroes.stream()
                .filter(hero -> hero.getRarity() == rarity)
                .toList();
        int randomPosition = RANDOM.nextInt(heroesWithRarity.size());
        return heroesWithRarity.get(randomPosition);
    }

    private Rarity decideRarity(int[] probabilities) {
        Rarity rarity = null;
        int rarityValue = 0;
        int randomChoices = RANDOM.nextInt(100);
        int sumProbability = 0;
        for (int probability : probabilities) {
            sumProbability += probability;
        }
        if (sumProbability != 100) {
            throw new IllegalArgumentException("Invalid pack probabilities");
        }
        for (int i = 0; i < probabilities.length; i++) {
            rarityValue = (i == 0) ? 0 : rarityValue + probabilities[i - 1];
            if (randomChoices <= rarityValue + probabilities[i]) {
                rarity = Rarity.values()[i];
                break;
            }
        }
        return rarity;
    }
}
