package fr.cleancode.org.server.mongo.mapper;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.server.mongo.entities.HeroEntity;

import java.util.List;
import java.util.stream.Collectors;

public interface HeroEntityMapper {

    static Hero toDomain(HeroEntity entity) {
        return Hero.builder()
                .heroId(entity.getHeroId())
                .name(entity.getName())
                .healthPoints(entity.getHealthPoints())
                .currentExperiences(entity.getCurrentExperiences())
                .power(entity.getPower())
                .armor(entity.getArmor())
                .speciality(entity.getSpeciality())
                .rarity(entity.getRarity())
                .level(entity.getLevel())
                .build();
    }

    static HeroEntity fromDomain(Hero domain) {
        return HeroEntity.builder()
                .heroId(domain.getHeroId())
                .name(domain.getName())
                .healthPoints(domain.getHealthPoints())
                .currentExperiences(domain.getCurrentExperiences())
                .power(domain.getPower())
                .armor(domain.getArmor())
                .speciality(domain.getSpeciality())
                .rarity(domain.getRarity())
                .level(domain.getLevel())
                .build();
    }

    static List<Hero> toDomainList(List<HeroEntity> entityList) {
        return entityList.stream()
                .map(entity -> toDomain(entity))
                .collect(Collectors.toList());
    }
}