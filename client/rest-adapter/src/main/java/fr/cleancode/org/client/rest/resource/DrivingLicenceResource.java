package fr.cleancode.org.client.rest.resource;

import fr.cleancode.org.client.rest.dto.DrivingLicenceCreationDto;
import fr.cleancode.org.client.rest.dto.TrafficOffenceCreationDto;
import fr.cleancode.org.client.rest.mapper.DrivingLicenceDtoMapper;
import fr.cleancode.org.domain.ports.client.DrivingLicenceCreatorApi;
import fr.cleancode.org.domain.ports.client.TrafficOffenceAppenderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static fr.cleancode.org.client.rest.mapper.DrivingLicenceDtoMapper.drivingLicenceCreationToDomain;
import static fr.cleancode.org.client.rest.mapper.TrafficOffenceDtoMapper.offenceCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/driving-licence")
public class DrivingLicenceResource {

  private final DrivingLicenceCreatorApi drivingLicenceCreatorApi;
  private final TrafficOffenceAppenderApi trafficOffenceAppenderApi;

  @PostMapping
  public ResponseEntity<Object> createDrivingLicence(@RequestBody DrivingLicenceCreationDto dto) {
    return drivingLicenceCreatorApi
        .create(drivingLicenceCreationToDomain(dto))
        .map(DrivingLicenceDtoMapper::toDto)
        .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
  }

  @PostMapping(path = "/{drivingLicenceId}/traffic-offence")
  public ResponseEntity<Object> appendTrafficOffence(
      @PathVariable("drivingLicenceId") UUID drivingLicenceId,
      @RequestBody TrafficOffenceCreationDto dto) {
    return trafficOffenceAppenderApi
        .appendOffence(drivingLicenceId, offenceCreationToDomain(dto))
        .map(DrivingLicenceDtoMapper::toDto)
        .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
  }
}
