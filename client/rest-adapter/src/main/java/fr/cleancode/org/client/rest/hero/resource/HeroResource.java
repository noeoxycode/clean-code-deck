package fr.cleancode.org.client.rest.hero.resource;

import fr.cleancode.org.client.rest.hero.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.hero.dto.HeroDto;
import fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper.heroCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroResource {

    private final HeroCreatorApi heroCreatorApi;

    private final HeroFinderApi heroFinderApi;

    @PostMapping
    public ResponseEntity<HeroDto> createHero(@RequestBody HeroCreationRequest request) {
        HeroDto heroDto = HeroDtoMapper
                .toDto(heroCreatorApi
                        .create(heroCreationToDomain(request)));
        return ResponseEntity.ok().body(heroDto);
    }

    @GetMapping
    public ResponseEntity<List<HeroDto>> findAllHeroes() {
        List<HeroDto> heroes = HeroDtoMapper
                .toDtoList(heroFinderApi
                        .findAllHeroes());
        return ResponseEntity.ok().body(heroes);
    }
}