package fr.cleancode.org.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.cleancode.org.domain.functional.model.hero.Rarity;
import fr.cleancode.org.domain.functional.model.hero.Speciality;

import java.util.UUID;

public record HeroCreationRequest(
        @JsonProperty("heroId") UUID heroId,
        @JsonProperty("name") String name,
        @JsonProperty("healthPoints") int healthPoints,
        @JsonProperty("currentExperiences") int currentExperiences,
        @JsonProperty("power") int power,
        @JsonProperty("armor") int armor,
        @JsonProperty("speciality") Speciality speciality,
        @JsonProperty("rarity") Rarity rarity,
        @JsonProperty("level") int level
) {
}