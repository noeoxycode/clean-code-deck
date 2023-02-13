package fr.cleancode.org.client.rest.hero.resource;

import fr.cleancode.org.client.rest.hero.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper.heroCreationToDomain;

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
    public Hero findHeroById(@PathVariable UUID heroId) {
        return heroFinderApi.findHeroById(heroId);
    }
}