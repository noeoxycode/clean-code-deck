package fr.cleancode.org.domain.player.functional.service;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerCreatorServiceTest {

    @InjectMocks
    private PlayerCreatorService service;

    @Mock
    private PlayerPersistenceSpi spi;

    @Test
    void should_create_player() {
        val given = Player.builder()
                .playerId(UUID.randomUUID())
                .pseudo("cawaa")
                .build();
        when(spi.create(given)).thenReturn(given);

        val actual = service.create(given);
        assertThat(actual).usingRecursiveComparison().isEqualTo(given);
    }

//    @Test
//    void should_not_create_player_if_is_null() {
//        val given = Player.builder()
//                .pseudo(null)
//                .build();
//        val actual = service.create(given);
//        assertThat(actual).isInstanceOf(ApplicationError.class);
//        verifyNoInteractions(spi);
//    }
}