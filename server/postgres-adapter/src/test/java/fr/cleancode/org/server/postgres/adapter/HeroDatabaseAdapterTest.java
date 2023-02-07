package fr.cleancode.org.server.postgres.adapter;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.functional.model.hero.Hero;
import fr.cleancode.org.server.postgres.entity.HeroEntity;
import fr.cleancode.org.server.postgres.mapper.HeroEntityMapper;
import fr.cleancode.org.server.postgres.repository.HeroRepository;
import lombok.val;
import org.assertj.vavr.api.VavrAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static fr.cleancode.org.domain.functional.model.hero.Rarity.LEGENDARY;
import static fr.cleancode.org.domain.functional.model.hero.Speciality.TANK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeroDatabaseAdapterTest {

    @InjectMocks
    private DrivingLicenceDatabaseAdapter adapter;

    @Mock
    private HeroRepository repository;

    @Nested
    class Save {

        @Captor
        private ArgumentCaptor<HeroEntity> entityCaptor;

        @Test
        void should_save() {
            val hero = Hero.builder()
                    .heroId(UUID.randomUUID())
                    .name("Dragon")
                    .healthPoints(1000)
                    .currentExperiences(10)
                    .power(100)
                    .armor(200)
                    .speciality(TANK)
                    .rarity(LEGENDARY)
                    .level(1)
                    .build();
            val entity = HeroEntityMapper.fromDomain(hero);

            when(repository.save(any(HeroEntity.class))).thenReturn(entity);

            val actual = adapter.save(hero);

            verify(repository).save(entityCaptor.capture());
            verifyNoMoreInteractions(repository);

            VavrAssertions.assertThat(actual).isRight().containsRightInstanceOf(Hero.class);
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(hero);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val hero = Hero.builder().build();
            val entity = HeroEntityMapper.fromDomain(hero);
            val throwable = new IllegalArgumentException();

            doThrow(throwable).when(repository).save(any(HeroEntity.class));

            val actual = adapter.save(hero);

            verify(repository).save(entityCaptor.capture());
            verifyNoMoreInteractions(repository);

            VavrAssertions.assertThat(actual).isLeft().containsLeftInstanceOf(ApplicationError.class);
            assertThat(actual.getLeft())
                    .usingRecursiveComparison()
                    .isEqualTo(new ApplicationError("Unable to save hero", null, hero, throwable));
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }
    }
}