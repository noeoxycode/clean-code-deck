package fr.cleancode.org.client.rest.fight.mapper;

import fr.cleancode.org.client.rest.fight.dto.FightCreationRequest;
import fr.cleancode.org.client.rest.fight.dto.FightDto;
import fr.cleancode.org.domain.fight.functional.model.Fight;


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

}
