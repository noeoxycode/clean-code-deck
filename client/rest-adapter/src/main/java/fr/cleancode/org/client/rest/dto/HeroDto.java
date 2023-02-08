package fr.cleancode.org.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.cleancode.org.domain.functional.model.hero.Rarity;
import fr.cleancode.org.domain.functional.model.hero.Speciality;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record HeroDto(
        String name,
        Speciality speciality,
        Rarity rarity
) {
}
