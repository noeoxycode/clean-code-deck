package fr.cleancode.org.client.rest.player.mapper;

import fr.cleancode.org.client.rest.player.dto.PlayerCreationRequest;
import fr.cleancode.org.client.rest.player.dto.PlayerDto;
import fr.cleancode.org.domain.player.functional.model.Player;

public interface PlayerDtoMapper {

    static PlayerDto toDto(Player player) {
        return new PlayerDto(
                player.getPlayerId(),
                player.getPseudo(),
                player.getToken(),
                player.getDeck(),
                player.getFight()
        );
    }

    static Player playerCreationToDomain(PlayerCreationRequest dto) {
        return Player.builder()
                .pseudo(dto.pseudo())
                .build();
    }
}