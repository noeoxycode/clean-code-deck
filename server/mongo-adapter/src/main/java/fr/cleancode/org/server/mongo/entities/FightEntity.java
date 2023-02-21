package fr.cleancode.org.server.mongo.entities;

import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document("fight")
public class FightEntity {

    @Id
    @Include
    private UUID fightId;

    private LocalDate date;

    private UUID attacker;

    private UUID defender;

    private UUID winner;

}