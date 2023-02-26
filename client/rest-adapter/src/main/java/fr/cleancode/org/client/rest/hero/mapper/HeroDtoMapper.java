package fr.cleancode.org.client.rest.hero.mapper;

import fr.cleancode.org.client.rest.hero.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.hero.dto.HeroDto;
import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                hero.getLevel()
        );
    }

    static Hero heroCreationToDomain(HeroCreationRequest dto) {
        return Hero.builder()
                .name(dto.name())
                .speciality(dto.speciality())
                .rarity(dto.rarity())
                .build();
    }

    static List<Hero> heroListCreationToDomain(List<HeroCreationRequest> dtoList) {
        ArrayList<Hero> heroes = new ArrayList<>();
        for(HeroCreationRequest dto : dtoList){
            Hero hero = Hero.builder()
                    .name(dto.name())
                    .speciality(dto.speciality())
                    .rarity(dto.rarity())
                    .build();
            heroes.add(hero);
        }
        return heroes;
    }

    static List<HeroDto> toDtoList(List<Hero> heroes) {
        return heroes.stream()
                .map(HeroDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
