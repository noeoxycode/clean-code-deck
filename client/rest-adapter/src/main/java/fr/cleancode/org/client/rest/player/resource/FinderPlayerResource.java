package fr.cleancode.org.client.rest.player.resource;

import fr.cleancode.org.client.rest.player.dto.PlayerDto;
import fr.cleancode.org.client.rest.player.mapper.PlayerDtoMapper;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.client.PlayerFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/player")
public class FinderPlayerResource {

    private final PlayerFinderApi playerFinderApi;

    @GetMapping(value = "/{playerId}")
    public ResponseEntity<PlayerDto> findPlayerById(@PathVariable UUID playerId) {
        Optional<Player> player = playerFinderApi.findPlayerById(playerId);
        return player.map(PlayerDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}