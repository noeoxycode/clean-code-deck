package fr.cleancode.org.client.rest.fight.mapper;

import fr.cleancode.org.client.rest.fight.dto.FightCreationRequest;
import fr.cleancode.org.client.rest.fight.dto.FightDto;
import fr.cleancode.org.client.rest.hero.dto.HeroDto;
import fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.List;
import java.util.stream.Collectors;


public class FightDtoMapper {

    public static Fight fightCreationToDomain(FightCreationRequest dto) {
        return Fight.builder()
                .attacker(dto.attacker())
                .defender(dto.defender())
                .build();
    }

    public static FightDto toDto(Fight fight) {
        return new FightDto(
                fight.getFightDate(),
                fight.getAttacker(),
                fight.getDefender(),
                fight.getWinner()
        );
    }

    public static List<FightDto> toDtoList(List<Fight> fightHistory) {
        return fightHistory.stream()
                .map(FightDtoMapper::toDto)
                .collect(Collectors.toList());
    }

}
