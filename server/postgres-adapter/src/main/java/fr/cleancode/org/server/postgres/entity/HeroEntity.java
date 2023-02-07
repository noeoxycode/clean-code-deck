package fr.cleancode.org.server.postgres.entity;

import fr.cleancode.org.domain.functional.model.hero.Rarity;
import fr.cleancode.org.domain.functional.model.hero.Speciality;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String availablePoints;
    private String name;
    private int healthPoints;
    private int currentExperiences;
    private int power;
    private int armor;
    private Speciality speciality;
    private Rarity rarity;
    private int level;
}