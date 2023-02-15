package fr.cleancode.org.domain.player.functional.service;

import fr.cleancode.org.domain.player.functional.exception.PlayerException;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerCreatorServiceTest {

    @InjectMocks
    private PlayerCreatorService service;

    @Mock
    private PlayerCreatorSpi spi;

    @Test
    void should_create_player() {
        val given = Player.builder()
                .playerId(UUID.randomUUID())
                .pseudo("Sacha")
                .deck(List.of())
                .token(4)
                .build();

        when(spi.create(given)).thenReturn(given);

        val actual = service.create(given);

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(given);
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_not_create_player_if_is_null() {
        val given = Player.builder()
                .playerId(null)
                .pseudo(null)
                .deck(null)
                .build();

        assertThatExceptionOfType(PlayerException.class)
                .isThrownBy(() ->
                        service.create(given));
        verifyNoInteractions(spi);
    }
}