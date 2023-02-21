package fr.cleancode.org.client.rest.pack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cleancode.org.domain.pack.functional.model.PackType;

public record PackCreationRequest(
        @JsonProperty("packType") PackType packType
) {
}