package fr.cleancode.org.domain.fight.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class Fight {

    @With
    @Default
    UUID fightId = UUID.randomUUID();

    @Default
    LocalDate fightDate = LocalDate.now();

    UUID attacker;

    UUID defender;

    UUID winner;
}
