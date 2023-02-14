package fr.cleancode.org.server.mongo.entities;

import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("hero")
public class HeroEntity {

    @Id
    @Include
    private UUID heroId;
    private String name;
    private int healthPoints;
    private int currentExperiences;
    private int power;
    private int armor;
    private Speciality speciality;
    private Rarity rarity;
    private int level;
}