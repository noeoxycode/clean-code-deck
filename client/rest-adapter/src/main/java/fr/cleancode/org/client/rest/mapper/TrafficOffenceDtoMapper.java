package fr.cleancode.org.client.rest.mapper;

import fr.cleancode.org.client.rest.dto.TrafficOffenceCreationDto;
import fr.cleancode.org.client.rest.dto.TrafficOffenceDto;
import fr.cleancode.org.domain.functional.model.TrafficOffence;

public interface TrafficOffenceDtoMapper {

  static TrafficOffence offenceCreationToDomain(TrafficOffenceCreationDto dto) {
    return TrafficOffence.builder()
        .label(dto.label())
        .details(dto.details())
        .pointsToWithdraw(dto.pointsToWithdraw())
        .build();
  }

  static TrafficOffenceDto toDto(TrafficOffence offence) {
    return new TrafficOffenceDto(
        offence.getId(), offence.getLabel(), offence.getDetails(), offence.getPointsToWithdraw());
  }
}
