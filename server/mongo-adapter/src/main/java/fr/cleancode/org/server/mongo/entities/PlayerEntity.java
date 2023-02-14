package fr.cleancode.org.server.mongo.entities;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private UUID playerId;
    private String pseudo;
    private int token;
    private List<Hero> deck;
}