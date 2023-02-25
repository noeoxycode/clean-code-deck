package fr.cleancode.org.domain.player.functional.model;

import fr.cleancode.org.domain.hero.functional.model.Hero;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

import java.util.ArrayList;
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
    ArrayList<Hero> deck = new ArrayList<>();
    @Default
    ArrayList<UUID> fights = new ArrayList<>();
}