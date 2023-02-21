package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;
import fr.cleancode.org.domain.pack.functional.service.generator.PackContentGeneratorService;
import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static fr.cleancode.org.domain.pack.functional.service.initialization.PackInitializer.initializePackPropertiesByType;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackContentServiceTest {

    @InjectMocks
    private PackContentService packContentService;

    @Mock
    private PackContentGeneratorService packContentGeneratorService;

    @Test
    void should_append_heroes_on_deck() {
        val player = Player
                .builder()
                .build();
        val packType = PackType.DIAMOND;
        val pack = Pack.builder().packType(packType).build();
        initializePackPropertiesByType(pack);
        final List<Hero> heroes = Arrays.asList(
                Hero.builder().build(),
                Hero.builder().build(),
                Hero.builder().build(),
                Hero.builder().build(),
                Hero.builder().build()
        );

        when(packContentGeneratorService.generateContent(pack))
                .thenReturn(heroes);

        val actual = packContentService.appendHeroesOnDeck(player, pack);

        assertThat(actual).usingRecursiveComparison()
                .isEqualTo(heroes);
        assertThat(player.getDeck()).isEqualTo(heroes);
        verify(packContentGeneratorService).generateContent(pack);
        verifyNoMoreInteractions(packContentGeneratorService);
    }

    @Test
    void should_not_append_heroes_on_deck() {
        val player = Player
                .builder()
                .build();
        val pack = Pack.builder().build();
        val throwable = new IllegalArgumentException();

        when(packContentGeneratorService.generateContent(pack))
                .thenThrow(throwable);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> packContentService.appendHeroesOnDeck(player, pack));
    }
}