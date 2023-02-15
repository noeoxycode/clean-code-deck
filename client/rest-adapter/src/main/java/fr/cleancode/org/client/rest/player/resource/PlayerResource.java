package fr.cleancode.org.client.rest.player.resource;

import fr.cleancode.org.client.rest.player.dto.PlayerCreationRequest;
import fr.cleancode.org.client.rest.player.dto.PlayerDto;
import fr.cleancode.org.client.rest.player.mapper.PlayerDtoMapper;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.client.PlayerCreatorApi;
import fr.cleancode.org.domain.player.ports.client.PlayerFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/player")
public class PlayerResource {

    private final PlayerCreatorApi playerCreatorApi;
    private final PlayerFinderApi playerFinderApi;

    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerCreationRequest request) {
        Player player = playerCreatorApi.create(PlayerDtoMapper
                .playerCreationToDomain(request));
        PlayerDto playerDto = PlayerDtoMapper.toDto(player);
        return ResponseEntity.ok().body(playerDto);
    }

    @GetMapping(value = "/{playerId}")
    public ResponseEntity<PlayerDto> findPlayerById(@PathVariable UUID playerId) {
        Player player = playerFinderApi.findPlayerById(playerId);
        PlayerDto playerDto = PlayerDtoMapper.toDto(player);
        return ResponseEntity.ok().body(playerDto);
    }
}