package fr.cleancode.org.domain.functional.model.hero;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class Hero {
    @Default
    UUID heroId = UUID.randomUUID();
    String name;
    int healthPoints;
    int currentExperiences;
    int power;
    int armor;
    Speciality speciality;
    Rarity rarity;
    int level;
}