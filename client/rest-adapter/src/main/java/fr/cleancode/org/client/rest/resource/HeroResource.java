package fr.cleancode.org.client.rest.resource;

import fr.cleancode.org.client.rest.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.mapper.HeroDtoMapper;
import fr.cleancode.org.domain.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.ports.client.HeroFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static fr.cleancode.org.client.rest.mapper.HeroDtoMapper.heroCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroResource {

    private final HeroCreatorApi heroCreatorApi;
    private final HeroFinderApi heroFinderApi;

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroCreationRequest request) {
        return heroCreatorApi
                .create(heroCreationToDomain(request))
                .map(HeroDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }

    @GetMapping(path = "/{heroId}")
    public ResponseEntity<Object> findHEro(@PathVariable ("heroId")UUID heroId) {
        return heroFinderApi
                .findHeroById(heroId)
                .map(HeroDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}