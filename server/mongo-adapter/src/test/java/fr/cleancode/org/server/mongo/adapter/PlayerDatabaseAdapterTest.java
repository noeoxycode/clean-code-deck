package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.server.mongo.entities.PlayerEntity;
import fr.cleancode.org.server.mongo.mapper.PlayerEntityMapper;
import fr.cleancode.org.server.mongo.repository.PlayerRepository;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerDatabaseAdapterTest {

    @InjectMocks
    private PlayerDatabaseAdapter playerDatabaseAdapter;

    @Mock
    private PlayerRepository playerRepository;

    @Nested
    class Create {

        @Captor
        private ArgumentCaptor<PlayerEntity> entityArgumentCaptor;

        @Test
        void should_create_player() {
            val player = Player.builder().build();
            val entity = PlayerEntityMapper.fromDomain(player);

            when(playerRepository
                    .save(any(PlayerEntity.class)))
                    .thenReturn(entity);

            val actual = playerDatabaseAdapter.create(player);

            verify(playerRepository)
                    .save(entityArgumentCaptor
                            .capture());
            verifyNoMoreInteractions(playerRepository);

            assertThat(actual)
                    .isInstanceOf(Player.class);
            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(player);
            assertThat(entityArgumentCaptor.getValue())
                    .usingRecursiveComparison()
                    .isEqualTo(entity);
        }

        @Test
        void should_not_create_player() {
            val player = Player.builder().build();
            val entity = PlayerEntityMapper.fromDomain(player);
            val throwable = new IllegalArgumentException();

            when(playerRepository.save(entity)).thenThrow(throwable);

            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> playerDatabaseAdapter.create(player));
            verify(playerRepository)
                    .save(entityArgumentCaptor
                            .capture());
            verifyNoMoreInteractions(playerRepository);
            assertThat(entityArgumentCaptor.getValue())
                    .usingRecursiveComparison()
                    .isEqualTo(entity);
        }
    }

    @Nested
    class FindById {

        @Test
        void should_find_player_by_id() {
            val id = UUID.randomUUID();
            val entity = PlayerEntity.builder().build();
            val domain = PlayerEntityMapper.toDomain(entity);

            when(playerRepository
                    .findPlayerEntitiesByPlayerId(id))
                    .thenReturn(entity);

            val actual = playerDatabaseAdapter.findPlayerById(id);

            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(domain);
            verifyNoMoreInteractions(playerRepository);
        }

        @Test
        void should_not_find_player_by_id() {
            val id = UUID.randomUUID();
            val throwable = new IllegalArgumentException();

            when(playerRepository.findPlayerEntitiesByPlayerId(id))
                    .thenThrow(throwable);

            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() ->
                            playerDatabaseAdapter.findPlayerById(id)
                    );
            verify(playerRepository).findPlayerEntitiesByPlayerId(id);
            verifyNoMoreInteractions(playerRepository);
        }
    }
}