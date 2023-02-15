package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
public class PackContentGeneratorService {
    private final HeroFinderApi heroFinderApi;
    public List<Hero> generateContent(Pack pack){
        List<Hero> packContent = null;
        List<Hero> heroesList = heroFinderApi.findAllHeroes();
        for(int i= 0; i < pack.cardsQuantity; i++){
            packContent.add(getHeroFromPackProba(heroesList, pack.proba));
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

    //care about null
    private Rarity decideRarity(int[] proba){
        Random random = new Random();
        Rarity rarity = null;
        int rarityValue = 0;
        int randomChoice = random.nextInt(100);
        for(int i = 0; i < proba.length; i++){
            rarityValue+= proba[i];
            if(randomChoice <= rarityValue){
                rarity = Rarity.values()[i];
            }
        }
        return rarity;
    }
}
