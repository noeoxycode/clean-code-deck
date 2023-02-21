package fr.cleancode.org.domain.pack.functional.service;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OpenPackServiceTest {

    @InjectMocks
    private OpenPackService openPackService;

    @Mock
    private PlayerFinderSpi playerFinderSpi;

    @Mock
    private PlayerCreatorSpi playerCreatorSpi;

    @Mock
    private PackContentService packContentService;

    @Test
    void should_open_pack() {
        val id = UUID.randomUUID();
        val player = Player.builder().playerId(id).build();
        val packType = PackType.DIAMOND;
        val heroes = List.of(
                Hero.builder().build(),
                Hero.builder().build(),
                Hero.builder().build(),
                Hero.builder().build()
        );

        when(playerFinderSpi.findPlayerById(id))
                .thenReturn(Optional.ofNullable(player));
        when(packContentService.appendHeroesOnDeck(any(Player.class),
                any(Pack.class)))
                .thenReturn(heroes);
        when(playerCreatorSpi.save(any(Player.class)))
                .thenReturn(player);

        val actual = openPackService.openPack(id, packType);

        assertThat(actual).isEqualTo(heroes);
        verify(playerFinderSpi).findPlayerById(id);
        verify(packContentService).appendHeroesOnDeck(any(Player.class),
                any(Pack.class));
        verify(playerCreatorSpi).save(any(Player.class));
        verifyNoMoreInteractions(playerFinderSpi,
                packContentService,
                playerCreatorSpi);
    }

    @Test
    void should_not_open_pack_if_player_is_not_found() {
        val id = UUID.randomUUID();
        val packType = PackType.DIAMOND;

        when(playerFinderSpi.findPlayerById(id))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> openPackService.openPack(id, packType));
        verify(playerFinderSpi).findPlayerById(id);
        verifyNoInteractions(playerCreatorSpi, packContentService);
    }

    @Test
    void should_not_open_pack_if_heroes_are_not_appended() {
        val id = UUID.randomUUID();
        val player = Player.builder().playerId(id).build();
        val packType = PackType.SILVER;
        val throwable = new IllegalArgumentException();

        when(playerFinderSpi.findPlayerById(id))
                .thenReturn(Optional.ofNullable(player));
        when(packContentService.appendHeroesOnDeck(any(Player.class), any(Pack.class)))
                .thenThrow(throwable);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> openPackService.openPack(id, packType));

        verify(playerFinderSpi).findPlayerById(id);
        verify(packContentService).appendHeroesOnDeck(any(Player.class), any(Pack.class));
        verifyNoInteractions(playerCreatorSpi);
    }

    @Test
    void should_not_open_pack_if_player_is_not_saved() {
        val id = UUID.randomUUID();
        val player = Player.builder().playerId(id).build();
        val packType = PackType.DIAMOND;
        val heroes = List.of(
                Hero.builder().build(),
                Hero.builder().build(),
                Hero.builder().build(),
                Hero.builder().build()
        );
        val throwable = new IllegalArgumentException();

        when(playerFinderSpi.findPlayerById(id))
                .thenReturn(Optional.ofNullable(player));
        when(packContentService.appendHeroesOnDeck(any(Player.class),
                any(Pack.class)))
                .thenReturn(heroes);
        when(playerCreatorSpi.save(any(Player.class)))
                .thenThrow(throwable);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> openPackService.openPack(id, packType));

        verify(playerFinderSpi).findPlayerById(id);
        verify(packContentService).appendHeroesOnDeck(any(Player.class),
                any(Pack.class));
        verify(playerCreatorSpi).save(any(Player.class));
    }
}