package fr.cleancode.org.client.rest.fight.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDate;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record FightDto(
        LocalDate localDate,
        UUID attacker,
        UUID defender,
        UUID winner
) {
}