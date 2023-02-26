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

import java.util.List;

import static fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper.heroListCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/createAllHeroes")
public class CreateAllHeroResource {

    private final HeroCreatorApi heroCreatorApi;

    @PostMapping
    public ResponseEntity<List<HeroDto>> createHero(@RequestBody List<HeroCreationRequest> request) {
        List<HeroDto> heroDto = HeroDtoMapper
                .toDtoList(heroCreatorApi
                        .saveAll(heroListCreationToDomain(request)));
        return ResponseEntity.ok().body(heroDto);
    }
}