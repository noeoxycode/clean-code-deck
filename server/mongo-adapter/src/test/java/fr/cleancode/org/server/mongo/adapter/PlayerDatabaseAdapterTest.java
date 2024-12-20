package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.server.mongo.entities.PlayerEntity;
import fr.cleancode.org.server.mongo.exception.PlayerNotFoundException;
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

import java.util.Optional;
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

            val actual = playerDatabaseAdapter.save(player);

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
                    .isThrownBy(() -> playerDatabaseAdapter.save(player));
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
            val entity = PlayerEntity
                    .builder()
                    .playerId(id)
                    .build();
            val domain = PlayerEntityMapper
                    .toDomain(entity);

            when(playerRepository
                    .findById(id))
                    .thenReturn(Optional.of(entity));

            val actual = playerDatabaseAdapter.findPlayerById(id).get();

            assertThat(actual).isEqualTo(domain);
            verify(playerRepository).findById(id);
            verifyNoMoreInteractions(playerRepository);
        }

        @Test
        void should_not_find_player_by_id() {
            val id = UUID.randomUUID();
            val throwable = new PlayerNotFoundException("The player was not found !");

            when(playerRepository.findById(id))
                    .thenThrow(throwable);

            assertThatExceptionOfType(PlayerNotFoundException.class)
                    .isThrownBy(() ->
                            playerDatabaseAdapter.findPlayerById(id)
                    );
            verify(playerRepository).findById(id);
            verifyNoMoreInteractions(playerRepository);
        }
    }
}