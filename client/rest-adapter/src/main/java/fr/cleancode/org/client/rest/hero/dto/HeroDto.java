package fr.cleancode.org.client.rest.hero.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.functional.model.Speciality;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record HeroDto(
        String name,
        Speciality speciality,
        Rarity rarity
) {
}