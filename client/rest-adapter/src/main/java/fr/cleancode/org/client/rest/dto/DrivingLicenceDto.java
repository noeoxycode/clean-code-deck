package fr.cleancode.org.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.vavr.collection.Seq;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record DrivingLicenceDto(
    UUID id,
    int availablePoints,
    Seq<TrafficOffenceDto> offences,
    String driverSSNumber
) {}
