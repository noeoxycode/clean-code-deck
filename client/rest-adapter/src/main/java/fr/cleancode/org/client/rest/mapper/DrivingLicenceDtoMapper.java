package fr.cleancode.org.client.rest.mapper;

import fr.cleancode.org.client.rest.dto.DrivingLicenceCreationDto;
import fr.cleancode.org.client.rest.dto.DrivingLicenceDto;
import fr.cleancode.org.domain.functional.model.DrivingLicence;

public interface DrivingLicenceDtoMapper {

  static DrivingLicenceDto toDto(DrivingLicence licence) {
    return new DrivingLicenceDto(
        licence.getId(),
        licence.getAvailablePoints(),
        licence.getOffences().map(TrafficOffenceDtoMapper::toDto),
        licence.getDriverSSNumber());
  }

  static DrivingLicence drivingLicenceCreationToDomain(DrivingLicenceCreationDto dto) {
    return DrivingLicence.builder().driverSSNumber(dto.driverSSNumber()).build();
  }
}
