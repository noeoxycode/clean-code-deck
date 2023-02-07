package fr.cleancode.org.client.rest.mapper;

import fr.cleancode.org.client.rest.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.dto.HeroDto;
import fr.cleancode.org.domain.functional.model.hero.Hero;

public interface HeroDtoMapper {

    static HeroDto toDto(Hero hero) {
        return new HeroDto(
                hero.getHeroId(),
                hero.getName(),
                hero.getHealthPoints(),
                hero.getCurrentExperiences(),
                hero.getPower(),
                hero.getArmor(),
                hero.getSpeciality(),
                hero.getRarity(),
                hero.getLevel());
    }

    static Hero heroCreationToDomain(HeroCreationRequest dto) {
        return Hero.builder()
                .heroId(dto.heroId())
                .name(dto.name())
                .healthPoints(dto.healthPoints())
                .currentExperiences(dto.currentExperiences())
                .power(dto.power())
                .armor(dto.armor())
                .speciality(dto.speciality())
                .rarity(dto.rarity())
                .level(dto.level())
                .build();
    }
}