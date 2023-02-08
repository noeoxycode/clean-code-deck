package fr.cleancode.org.domain.functional.model.hero;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class Hero {

    @Default
    UUID heroId = UUID.randomUUID();
    String name;
    int healthPoints;
    @Default
    int currentExperiences = 0;
    int power;
    int armor;
    Speciality speciality;
    Rarity rarity;
    @Default
    int level = 1;
}