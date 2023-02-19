package fr.cleancode.org.domain.fight.functional.model;


import fr.cleancode.org.domain.hero.functional.model.Hero;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
public class Fight {
    @Default
    LocalDate date = LocalDate.now();

    UUID attacker;

    UUID defender;

    UUID winner;
}
