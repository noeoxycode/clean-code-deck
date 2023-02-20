package fr.cleancode.org.client.rest.fight.ressource;

import fr.cleancode.org.client.rest.fight.dto.FightCreationRequest;
import fr.cleancode.org.client.rest.fight.dto.FightDto;
import fr.cleancode.org.client.rest.fight.mapper.FightDtoMapper;
import fr.cleancode.org.domain.fight.port.client.Fightapi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static fr.cleancode.org.client.rest.fight.mapper.FightDtoMapper.fightCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fight")
public class FightRessource {

    private final Fightapi fightapi;

    @PostMapping
    public ResponseEntity<FightDto> fight(@RequestBody FightCreationRequest request) {
        FightDto fightDto = FightDtoMapper
                .toDto(fightapi
                        .fight(fightCreationToDomain(request), request.player()));
        return ResponseEntity.ok().body(fightDto);
    }
}