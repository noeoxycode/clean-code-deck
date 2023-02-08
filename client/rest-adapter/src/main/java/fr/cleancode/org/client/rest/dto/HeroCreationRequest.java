package fr.cleancode.org.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cleancode.org.domain.functional.model.hero.Rarity;
import fr.cleancode.org.domain.functional.model.hero.Speciality;

public record HeroCreationRequest(
        @JsonProperty("name") String name,
        @JsonProperty("speciality") Speciality speciality,
        @JsonProperty("rarity") Rarity rarity
) {
}