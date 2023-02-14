package fr.cleancode.org.client.rest.player.resource;

import fr.cleancode.org.client.rest.player.dto.PlayerCreationRequest;
import fr.cleancode.org.client.rest.player.dto.PlayerDto;
import fr.cleancode.org.client.rest.player.mapper.PlayerDtoMapper;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.client.PlayerCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/player")
public class PlayerResource {

    private final PlayerCreatorApi playerCreatorApi;

    @PostMapping
    public PlayerDto createPlayer(@RequestBody PlayerCreationRequest request) {
        Player player = playerCreatorApi.create(PlayerDtoMapper.playerCreationToDomain(request));
        return PlayerDtoMapper.toDto(player);

    }
}