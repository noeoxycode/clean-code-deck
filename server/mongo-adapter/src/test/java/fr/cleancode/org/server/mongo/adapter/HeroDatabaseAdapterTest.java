package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import fr.cleancode.org.server.mongo.entities.HeroEntity;
import fr.cleancode.org.server.mongo.mapper.HeroEntityMapper;
import fr.cleancode.org.server.mongo.repository.HeroRepository;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static fr.cleancode.org.domain.hero.functional.model.Rarity.LEGENDARY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HeroDatabaseAdapterTest {

    @InjectMocks
    private HeroDatabaseAdapter heroDatabaseAdapter;

    @Mock
    private HeroRepository heroRepository;

    @Nested
    class Create {

        @Captor
        private ArgumentCaptor<HeroEntity> entityArgumentCaptor;

        @Captor
        private ArgumentCaptor<List<HeroEntity>> listEntityArgumentCaptor;

        @Test
        void should_create_hero() {
            val hero = Hero.builder().build();
            val entity = HeroEntityMapper.fromDomain(hero);

            when(heroRepository
                    .save(any(HeroEntity.class)))
                    .thenReturn(entity);

            val actual = heroDatabaseAdapter.save(hero);

            verify(heroRepository)
                    .save(entityArgumentCaptor
                            .capture());
            verifyNoMoreInteractions(heroRepository);

            assertThat(actual)
                    .isInstanceOf(Hero.class);
            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(hero);
            assertThat(entityArgumentCaptor.getValue())
                    .usingRecursiveComparison()
                    .isEqualTo(entity);
        }

        @Test
        void should_not_create_hero() {
            val hero = Hero.builder().build();
            val entity = HeroEntityMapper.fromDomain(hero);
            val throwable = new IllegalArgumentException();

            when(heroRepository.save(entity)).thenThrow(throwable);

            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> heroDatabaseAdapter.save(hero));
            verify(heroRepository)
                    .save(entityArgumentCaptor
                            .capture());
            verifyNoMoreInteractions(heroRepository);
            assertThat(entityArgumentCaptor.getValue())
                    .usingRecursiveComparison()
                    .isEqualTo(entity);
        }


        @Test
        void should_create_heroes() {
            val hero1 = Hero.builder().build();
            val entity1 = HeroEntityMapper.fromDomain(hero1);

            val hero2 = Hero.builder().build();
            val entity2 = HeroEntityMapper.fromDomain(hero2);

            val heroes = Arrays.asList(hero1, hero2);
            val entities = Arrays.asList(entity1, entity2);

            when(heroRepository.saveAll(anyList())).thenReturn(entities);

            val actual = heroDatabaseAdapter.saveAll(heroes);

            verify(heroRepository).saveAll(listEntityArgumentCaptor.capture());
            verifyNoMoreInteractions(heroRepository);

            assertThat(actual).isInstanceOf(List.class);
            assertThat(actual).usingRecursiveComparison().isEqualTo(heroes);
            assertThat(listEntityArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(entities);
        }

        @Test
        void should_not_create_heroes() {
            val hero1 = Hero.builder().build();
            val entity1 = HeroEntityMapper.fromDomain(hero1);

            val hero2 = Hero.builder().build();
            val entity2 = HeroEntityMapper.fromDomain(hero2);

            val heroes = Arrays.asList(hero1, hero2);
            val entities = Arrays.asList(entity1, entity2);

            val throwable = new IllegalArgumentException();

            when(heroRepository.saveAll(entities)).thenThrow(throwable);

            assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> heroDatabaseAdapter.saveAll(heroes));

            verify(heroRepository).saveAll(listEntityArgumentCaptor.capture());
            verifyNoMoreInteractions(heroRepository);

            assertThat(listEntityArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(entities);
        }

    }

    @Nested
    class FindAll {

        @Test
        void should_find_heroes() {
            List<HeroEntity> heroes = List.of(
                    HeroEntity.builder()
                            .heroId(UUID.randomUUID())
                            .name("Dragon")
                            .healthPoints(1000)
                            .currentExperiences(10)
                            .power(100)
                            .armor(200)
                            .speciality(Speciality.TANK)
                            .rarity(LEGENDARY)
                            .level(1)
                            .build(),
                    HeroEntity.builder()
                            .heroId(UUID.randomUUID())
                            .name("Dragon")
                            .healthPoints(1000)
                            .currentExperiences(10)
                            .power(100)
                            .armor(200)
                            .speciality(Speciality.TANK)
                            .rarity(LEGENDARY)
                            .level(1)
                            .build(),
                    HeroEntity.builder()
                            .heroId(UUID.randomUUID())
                            .name("Dragon")
                            .healthPoints(1000)
                            .currentExperiences(10)
                            .power(100)
                            .armor(200)
                            .speciality(Speciality.TANK)
                            .rarity(LEGENDARY)
                            .level(1)
                            .build()
            );
            val given = HeroEntityMapper.toDomainList(heroes);

            when(heroRepository.findAll())
                    .thenReturn(heroes);

            final var actual = heroDatabaseAdapter.findAllHeroes();

            assertEquals(actual, given);
            verify(heroRepository).findAll();
            verifyNoMoreInteractions(heroRepository);
        }

        @Test
        void should_not_find_heroes() {
            when(heroRepository.findAll()).thenReturn(List.of());

            final var actual = heroDatabaseAdapter.findAllHeroes();

            assertEquals(actual, List.of());
            verify(heroRepository).findAll();
            verifyNoMoreInteractions(heroRepository);
        }
    }
}