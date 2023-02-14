package fr.cleancode.org.server.mongo.entities;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("player")
public class PlayerEntity {

    @Id
    @Include
    @Column(
            name = "playerId",
            unique = true,
            nullable = false,
            updatable = false
    )
    private UUID playerId;
    @Column(
            name = "pseudo",
            nullable = false
    )
    private String pseudo;
    @Column(
            name = "token",
            nullable = false
    )
    private int token;
    @Column(
            name = "deck",
            nullable = false,
            unique = true
    )
    @ElementCollection
    private List<Hero> deck;
}