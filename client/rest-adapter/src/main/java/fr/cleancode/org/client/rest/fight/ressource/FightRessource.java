package fr.cleancode.org.client.rest.fight.ressource;

import fr.cleancode.org.client.rest.fight.dto.FightCreationRequest;
import fr.cleancode.org.client.rest.fight.dto.FightDto;
import fr.cleancode.org.client.rest.fight.mapper.FightDtoMapper;
import fr.cleancode.org.domain.fight.port.client.FightCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.cleancode.org.client.rest.fight.mapper.FightDtoMapper.fightCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/fight")
public class FightRessource {

    private final FightCreatorApi fightApi;

    @PostMapping
    public ResponseEntity<FightDto> fight(@RequestBody FightCreationRequest request) {
        FightDto fightDto = FightDtoMapper
                .toDto(fightApi
                        .saveFifght(fightCreationToDomain(request), request.player()));
        return ResponseEntity.ok().body(fightDto);
    }
}