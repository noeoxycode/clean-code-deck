package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.server.mongo.entities.FightEntity;
import fr.cleancode.org.server.mongo.entities.HeroEntity;
import fr.cleancode.org.server.mongo.mapper.FightEntityMapper;
import fr.cleancode.org.server.mongo.mapper.HeroEntityMapper;
import fr.cleancode.org.server.mongo.repository.FightRepository;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static fr.cleancode.org.domain.hero.functional.model.Rarity.LEGENDARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FightDatabaseAdapterTest {

    @InjectMocks
    private FightDatabaseAdapter fightDatabaseAdapter;

    @Mock
    private FightRepository fightRepository;

    @Nested
    class Create {

        @Captor
        private ArgumentCaptor<FightEntity> entityArgumentCaptor;

        @Test
        void should_create_fight() {
            val fight = Fight.builder()
                    .winner(UUID.randomUUID())
                    .fightId(UUID.randomUUID())
                    .attacker(UUID.randomUUID())
                    .defender(UUID.randomUUID())
                    .build();
            val entity = FightEntityMapper.fromDomain(fight);

            when(fightRepository
                    .save(any(FightEntity.class)))
                    .thenReturn(entity);

            val actual = fightDatabaseAdapter.save(fight);

            verify(fightRepository)
                    .save(entityArgumentCaptor
                            .capture());
            verifyNoMoreInteractions(fightRepository);

            assertThat(actual)
                    .isInstanceOf(Fight.class);
            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(fight);
            assertThat(entityArgumentCaptor.getValue())
                    .usingRecursiveComparison()
                    .isEqualTo(entity);
        }

        @Test
        void should_not_create_fight() {
            val fight = Fight.builder().build();
            val entity = FightEntityMapper.fromDomain(fight);
            val throwable = new IllegalArgumentException();

            when(fightRepository.save(entity)).thenThrow(throwable);

            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> fightDatabaseAdapter.save(fight));
            verify(fightRepository)
                    .save(entityArgumentCaptor
                            .capture());
            verifyNoMoreInteractions(fightRepository);
            assertThat(entityArgumentCaptor.getValue())
                    .usingRecursiveComparison()
                    .isEqualTo(entity);
        }

    }

    @Nested
    class FindAll {

        @Test
        void should_find_fights() {
            List<FightEntity> fights = List.of(
                    FightEntity.builder()
                            .winner(UUID.randomUUID())
                            .fightId(UUID.randomUUID())
                            .attacker(UUID.randomUUID())
                            .defender(UUID.randomUUID())
                            .build(),
                    FightEntity.builder()
                            .winner(UUID.randomUUID())
                            .fightId(UUID.randomUUID())
                            .attacker(UUID.randomUUID())
                            .defender(UUID.randomUUID())
                            .build(),
                    FightEntity.builder()
                            .winner(UUID.randomUUID())
                            .fightId(UUID.randomUUID())
                            .attacker(UUID.randomUUID())
                            .defender(UUID.randomUUID())
                            .build()
            );
            val given = FightEntityMapper.toDomainList(fights);

            when(fightRepository.findAll())
                    .thenReturn(fights);

            final var actual = fightDatabaseAdapter.findAllFights();

            assertEquals(actual, given);
            verify(fightRepository).findAll();
            verifyNoMoreInteractions(fightRepository);
        }

        @Test
        void should_not_find_Fights() {
            when(fightRepository.findAll()).thenReturn(List.of());

            final var actual = fightDatabaseAdapter.findAllFights();

            assertEquals(actual, List.of());
            verify(fightRepository).findAll();
            verifyNoMoreInteractions(fightRepository);
        }
    }
}