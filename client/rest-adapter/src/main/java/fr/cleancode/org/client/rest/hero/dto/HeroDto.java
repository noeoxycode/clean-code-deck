package fr.cleancode.org.client.rest.hero.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.functional.model.Speciality;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record HeroDto(
        UUID heroId,
        String name,
        int healthPoints,
        int currentExperiences,
        int power,
        int armor,
        Speciality speciality,
        Rarity rarity,
        int level
) {
}