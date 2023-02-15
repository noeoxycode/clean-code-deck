package fr.cleancode.org.client.rest.hero.resource;

import fr.cleancode.org.client.rest.hero.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.hero.dto.HeroDto;
import fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper.heroCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class CreateHeroResource {

    private final HeroCreatorApi heroCreatorApi;

    @PostMapping
    public ResponseEntity<HeroDto> createHero(@RequestBody HeroCreationRequest request) {
        HeroDto heroDto = HeroDtoMapper
                .toDto(heroCreatorApi
                        .create(heroCreationToDomain(request)));
        return ResponseEntity.ok().body(heroDto);
    }
}