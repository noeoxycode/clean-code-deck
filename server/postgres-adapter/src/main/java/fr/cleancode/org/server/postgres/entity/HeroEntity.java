package fr.cleancode.org.server.postgres.entity;

import fr.cleancode.org.domain.functional.model.hero.Rarity;
import fr.cleancode.org.domain.functional.model.hero.Speciality;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "hero")
public class HeroEntity {

    @Id
    @Include
    private UUID heroId;
    @Column(
            nullable = false,
            unique = true
    )
    private String name;
    @Column(
            nullable = false
    )
    private int healthPoints;
    @Column(
            nullable = false
    )
    private int currentExperiences;
    @Column(
            nullable = false
    )
    private int power;
    @Column(
            nullable = false
    )
    private int armor;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Enumerated(EnumType.STRING)
    private Rarity rarity;
    @Column(
            nullable = false
    )
    private int level;
}