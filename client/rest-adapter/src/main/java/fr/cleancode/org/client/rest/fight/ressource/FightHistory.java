package fr.cleancode.org.client.rest.fight.ressource;

import fr.cleancode.org.client.rest.fight.dto.FightCreationRequest;
import fr.cleancode.org.client.rest.fight.dto.FightDto;
import fr.cleancode.org.client.rest.fight.mapper.FightDtoMapper;
import fr.cleancode.org.client.rest.player.dto.PlayerDto;
import fr.cleancode.org.client.rest.player.mapper.PlayerDtoMapper;
import fr.cleancode.org.domain.fight.functional.model.Fight;
import fr.cleancode.org.domain.fight.port.client.FightCreatorApi;
import fr.cleancode.org.domain.fight.port.client.FightFinderApi;
import fr.cleancode.org.domain.player.functional.model.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static fr.cleancode.org.client.rest.fight.mapper.FightDtoMapper.fightCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fight")
public class FightHistory {

    private final FightFinderApi fightFinderApi;

    @GetMapping(value = "/history/{heroId}")
    public ResponseEntity<List<FightDto>> fight(@PathVariable UUID heroId) {
        Optional<List<Fight>> fightHistory = fightFinderApi.findFightsHistory(heroId);
        return fightHistory.map(FightDtoMapper::toDtoList)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}