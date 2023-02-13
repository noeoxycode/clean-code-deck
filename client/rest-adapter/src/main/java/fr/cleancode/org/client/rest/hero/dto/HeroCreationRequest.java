package fr.cleancode.org.client.rest.hero.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.functional.model.Speciality;

public record HeroCreationRequest(
        @JsonProperty("name") String name,
        @JsonProperty("speciality") Speciality speciality,
        @JsonProperty("rarity") Rarity rarity
) {
}