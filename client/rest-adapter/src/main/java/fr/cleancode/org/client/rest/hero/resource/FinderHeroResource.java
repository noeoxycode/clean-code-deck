package fr.cleancode.org.client.rest.hero.resource;

import fr.cleancode.org.client.rest.hero.dto.HeroDto;
import fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class FinderHeroResource {
    private final HeroFinderApi heroFinderApi;

    @GetMapping
    public ResponseEntity<List<HeroDto>> findAllHeroes() {
        List<HeroDto> heroes = HeroDtoMapper
                .toDtoList(heroFinderApi
                        .findAllHeroes());
        return ResponseEntity.ok().body(heroes);
    }
}