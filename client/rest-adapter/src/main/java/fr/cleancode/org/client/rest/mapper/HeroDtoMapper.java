package fr.cleancode.org.client.rest.mapper;

import fr.cleancode.org.client.rest.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.dto.HeroDto;
import fr.cleancode.org.domain.functional.model.hero.Hero;

public interface HeroDtoMapper {

    static HeroDto toDto(Hero hero) {
        return new HeroDto(
                hero.getName(),
                hero.getSpeciality(),
                hero.getRarity());
    }

    static Hero heroCreationToDomain(HeroCreationRequest dto) {
        return Hero.builder()
                .name(dto.name())
                .speciality(dto.speciality())
                .rarity(dto.rarity())
                .build();
    }
}