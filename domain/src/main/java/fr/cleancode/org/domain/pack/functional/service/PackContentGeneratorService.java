package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class PackContentGeneratorService {
    private final HeroFinderApi heroFinderApi;
    public List<Hero> generateContent(Pack pack){
        List<Hero> packContent = new ArrayList<>();
        List<Hero> heroesList = heroFinderApi.findAllHeroes();
        for(int i= 0; i < pack.getCardsQuantity(); i++){
            packContent.add(getHeroFromPackProba(heroesList, pack.getProba()));
        }
        return packContent;
    }

    private Hero getHeroFromPackProba(List<Hero> heroesList, int[] proba){
        Rarity rarity = decideRarity(proba);
        List<Hero> heroesWithRarity = heroesList.stream()
                .filter(hero -> hero.getRarity() == rarity)
                .toList();
        Random random = new Random();
        int randomPosition = random.nextInt(heroesWithRarity.size());
        return heroesWithRarity.get(randomPosition);
    }

    private Rarity decideRarity(int[] proba) throws IllegalArgumentException {
        Random random = new Random();
        Rarity rarity = null;
        int rarityValue = 0;
        int randomChoice = random.nextInt(100);
        int sumProba = 0;
        for (int p : proba) {
            sumProba += p;
        }
        if (sumProba != 100) {
            throw new IllegalArgumentException("Invalid pack probabilities");
        }
        for (int i = 0; i < proba.length; i++) {
            rarityValue = (i == 0) ? 0 : rarityValue + proba[i - 1];
            if (randomChoice <= rarityValue + proba[i]) {
                rarity = Rarity.values()[i];
                break;
            }
        }
        return rarity;
    }
}
