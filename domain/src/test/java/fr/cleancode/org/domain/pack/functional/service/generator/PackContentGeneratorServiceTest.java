package fr.cleancode.org.domain.pack.functional.service.generator;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PackContentGeneratorServiceTest {

    @InjectMocks
    private PackContentGeneratorService generatorService;

    @Mock
    private HeroFinderSpi heroFinderSpi;

    @Test
    public void test_generate_content() {
        Pack pack = Pack.builder().build();
        pack.setProbability(new int[]{30, 40, 30});
        List<Hero> heroesList = Arrays.asList(
                Hero.builder().rarity(Rarity.COMMON).build(),
                Hero.builder().rarity(Rarity.COMMON).build(),
                Hero.builder().rarity(Rarity.RARE).build(),
                Hero.builder().rarity(Rarity.RARE).build(),
                Hero.builder().rarity(Rarity.LEGENDARY).build(),
                Hero.builder().rarity(Rarity.LEGENDARY).build()
        );

        when(heroFinderSpi.findAllHeroes()).thenReturn(heroesList);

        List<Hero> packContent = generatorService.generateContent(pack);

        assertEquals(pack.getCardsQuantity(), packContent.size());
        for (Hero hero : packContent) {
            assertTrue(heroesList.contains(hero));
        }
    }

    @Test
    public void test_generate_content_with_invalid_probability() {
        Pack pack = Pack.builder().build();
        pack.setProbability(new int[]{50, 50, 50});

        val throwable = new IllegalArgumentException();

        when(generatorService.generateContent(pack))
                .thenThrow(throwable);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> generatorService.generateContent(pack));
    }

    @Test
    public void test_probability() {
        List<Hero> heroesList = new ArrayList<>();
        heroesList.add(Hero.builder().name("Hero1").rarity(Rarity.COMMON).build());
        heroesList.add(Hero.builder().name("Hero2").rarity(Rarity.COMMON).build());
        heroesList.add(Hero.builder().name("Hero3").rarity(Rarity.COMMON).build());
        heroesList.add(Hero.builder().name("Hero4").rarity(Rarity.RARE).build());
        heroesList.add(Hero.builder().name("Hero5").rarity(Rarity.RARE).build());
        heroesList.add(Hero.builder().name("Hero6").rarity(Rarity.RARE).build());
        heroesList.add(Hero.builder().name("Hero7").rarity(Rarity.LEGENDARY).build());
        heroesList.add(Hero.builder().name("Hero8").rarity(Rarity.LEGENDARY).build());
        heroesList.add(Hero.builder().name("Hero9").rarity(Rarity.LEGENDARY).build());
        when(heroFinderSpi.findAllHeroes()).thenReturn(heroesList);

        int nbIterations = 10000;

        Pack pack = Pack.builder().build();
        pack.setProbability(new int[]{50, 30, 20});

        Map<Rarity, Integer> heroByRarity = new HashMap<>();
        heroByRarity.put(Rarity.COMMON, 0);
        heroByRarity.put(Rarity.RARE, 0);
        heroByRarity.put(Rarity.LEGENDARY, 0);

        for (int i = 0; i < nbIterations; i++) {
            List<Hero> packContent = generatorService.generateContent(pack);
            for (Hero hero : packContent) {
                heroByRarity.put(hero.getRarity(), heroByRarity.get(hero.getRarity()) + 1);
            }
        }

        int totalCommon = heroByRarity.get(Rarity.COMMON);
        int totalRare = heroByRarity.get(Rarity.RARE);
        int totalLegendary = heroByRarity.get(Rarity.LEGENDARY);

        int expectedTotalCommun = nbIterations * pack.getCardsQuantity() * pack.getProbability()[0] / 100;
        int expectedTotalRare = nbIterations * pack.getCardsQuantity() * pack.getProbability()[1] / 100;
        int expectedTotalLegendary = nbIterations * pack.getCardsQuantity() * pack.getProbability()[2] / 100;

        assertEquals(expectedTotalCommun, totalCommon, nbIterations * 0.1);
        assertEquals(expectedTotalRare, totalRare, nbIterations * 0.1);
        assertEquals(expectedTotalLegendary, totalLegendary, nbIterations * 0.1);
    }

}
