package fr.cleancode.org.domain.pack.fonctional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;
import fr.cleancode.org.domain.pack.functional.service.PackContentGeneratorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PackContentGeneratorServiceTest {

    @Mock
    HeroFinderApi heroFinderApi;

    @InjectMocks
    PackContentGeneratorService generatorService;

    @Test
    public void testGenerateContent() throws IllegalArgumentException {
        Pack pack = PackType.SILVER.value;
        pack.setProba(new int[]{30, 40, 30});
        List<Hero> heroesList = Arrays.asList(
                Hero.builder().rarity(Rarity.COMMON).build(),
                Hero.builder().rarity(Rarity.COMMON).build(),
                Hero.builder().rarity(Rarity.RARE).build(),
                Hero.builder().rarity(Rarity.RARE).build(),
                Hero.builder().rarity(Rarity.LEGENDARY).build(),
                Hero.builder().rarity(Rarity.LEGENDARY).build()
        );

        when(heroFinderApi.findAllHeroes()).thenReturn(heroesList);

        List<Hero> packContent = generatorService.generateContent(pack);

        assertEquals(pack.getCardsQuantity(), packContent.size());
        for (Hero hero : packContent) {
            assertTrue(heroesList.contains(hero));
        }
    }

    @Test
    public void testGenerateContent_withInvalidPackProba_shouldThrowIllegalArgumentException() {
        Pack pack = PackType.SILVER.value;
        pack.setProba(new int[]{50, 50, 50});

        assertThrows(IllegalArgumentException.class, () -> generatorService.generateContent(pack));
    }

    @Test
    public void testGenerateContent_withNoHeroWithSelectedRarity_shouldThrowIllegalArgumentException() throws IllegalArgumentException {
        Pack pack = PackType.SILVER.value;
        pack.setProba(new int[]{0, 100, 0});
        List<Hero> heroesList = Arrays.asList(
                Hero.builder().rarity(Rarity.COMMON).build(),
                Hero.builder().rarity(Rarity.COMMON).build(),
                Hero.builder().rarity(Rarity.LEGENDARY).build(),
                Hero.builder().rarity(Rarity.LEGENDARY).build()
        );
        when(heroFinderApi.findAllHeroes()).thenReturn(heroesList);

        assertThrows(IllegalArgumentException.class, () -> generatorService.generateContent(pack));
    }

}

