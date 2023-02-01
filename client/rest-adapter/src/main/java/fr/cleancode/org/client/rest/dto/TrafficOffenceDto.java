package fr.cleancode.org.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record TrafficOffenceDto(
   UUID id,
   String label,
   String details,
   int pointsToWithdraw
) {}
