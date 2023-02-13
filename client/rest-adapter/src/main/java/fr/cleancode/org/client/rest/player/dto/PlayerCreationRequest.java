package fr.cleancode.org.client.rest.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayerCreationRequest(
        @JsonProperty("pseudo") String pseudo
) {
}