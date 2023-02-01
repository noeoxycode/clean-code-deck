package fr.cleancode.org.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DrivingLicenceCreationDto(@JsonProperty("driverSSNumber") String driverSSNumber) {}
