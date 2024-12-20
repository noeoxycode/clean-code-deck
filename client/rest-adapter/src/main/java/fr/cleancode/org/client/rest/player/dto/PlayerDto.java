package fr.cleancode.org.client.rest.player.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fr.cleancode.org.domain.hero.functional.model.Hero;

import java.util.ArrayList;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record PlayerDto(
        UUID playerId,
        String pseudo,
        int token,
        ArrayList<Hero> deck,
        ArrayList<UUID> fights
) {
}