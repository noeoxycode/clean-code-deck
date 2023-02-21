package fr.cleancode.org.domain.player.functional.model;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class Player {

    @Default
    UUID playerId = UUID.randomUUID();
    String pseudo;
    @Default
    int token = 4;
    @Default
    List<Hero> deck = List.of();

    @Default
    List<UUID> fights = List.of();
}