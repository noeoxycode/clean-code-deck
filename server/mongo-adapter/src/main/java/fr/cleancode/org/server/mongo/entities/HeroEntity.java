package fr.cleancode.org.server.mongo.entities;

import fr.cleancode.org.domain.hero.functional.model.Rarity;
import fr.cleancode.org.domain.hero.functional.model.Speciality;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
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
    @Column(
            name = "heroId",
            updatable = false,
            nullable = false,
            unique = true
    )
    private UUID heroId;
    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;
    @Column(
            name = "healthPoints",
            nullable = false
    )
    private int healthPoints;
    @Column(
            name = "currentExperiences",
            nullable = false
    )
    private int currentExperiences;
    @Column(
            name = "power",
            nullable = false
    )
    private int power;
    @Column(
            name = "armor",
            nullable = false
    )
    private int armor;
    @Enumerated(EnumType.STRING)
    @Column(
            name = "speciality",
            nullable = false
    )
    private Speciality speciality;
    @Enumerated(EnumType.STRING)
    @Column(
            name = "rarity",
            nullable = false
    )
    private Rarity rarity;
    @Column(
            name = "level",
            nullable = false
    )
    private int level;
}