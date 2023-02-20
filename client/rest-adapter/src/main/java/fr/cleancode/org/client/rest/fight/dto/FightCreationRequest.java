package fr.cleancode.org.client.rest.fight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record FightCreationRequest(
        @JsonProperty("player") UUID player,
        @JsonProperty("attacker") UUID attacker,
        @JsonProperty("defender") UUID defender
) {
}