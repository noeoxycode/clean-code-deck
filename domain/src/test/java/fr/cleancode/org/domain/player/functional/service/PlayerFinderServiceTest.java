package fr.cleancode.org.domain.player.functional.service;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerFinderServiceTest {

    @InjectMocks
    private PlayerFinderService service;

    @Mock
    private PlayerFinderSpi spi;

    @Test
    void should_find_player_with_id() {
        val given = UUID.randomUUID();
        val player = Optional.of(Player.builder()
                .playerId(given)
                .pseudo("sacha")
                .deck(new ArrayList<>())
                .token(4)
                .build());

        when(spi.findPlayerById(given)).thenReturn(player);

        final var actual = service.findPlayerById(given);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(player);
        verify(spi).findPlayerById(given);
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_not_find_player_with_id() {
        val given = UUID.randomUUID();

        when(spi.findPlayerById(given)).thenReturn(Optional.empty());

        final var actual = service.findPlayerById(given);

        assertThat(actual).isEmpty();
        verify(spi).findPlayerById(given);
        verifyNoMoreInteractions(spi);
    }
}