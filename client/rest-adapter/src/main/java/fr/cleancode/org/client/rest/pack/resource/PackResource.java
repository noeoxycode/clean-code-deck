package fr.cleancode.org.client.rest.pack.resource;

import fr.cleancode.org.client.rest.hero.dto.HeroDto;
import fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper;
import fr.cleancode.org.client.rest.pack.dto.PackCreationRequest;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.ports.client.OpenPackApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/openPack/{playerId}")
public class PackResource {

    private final OpenPackApi openPackApi;

    @PostMapping
    public List<HeroDto> openPack(
            @PathVariable UUID playerId,
            @RequestBody PackCreationRequest request
    ) {
        List<Hero> packContent = openPackApi
                .openPack(playerId, request.packType());
        return HeroDtoMapper.toDtoList(packContent);
    }
}