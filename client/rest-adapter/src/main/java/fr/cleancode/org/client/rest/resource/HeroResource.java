package fr.cleancode.org.client.rest.resource;

import fr.cleancode.org.client.rest.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.mapper.HeroDtoMapper;
import fr.cleancode.org.domain.ports.client.HeroCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.cleancode.org.client.rest.mapper.HeroDtoMapper.heroCreationToDomain;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/hero")
public class HeroResource {

    private final HeroCreatorApi heroCreatorApi;

    @PostMapping
    public ResponseEntity<Object> createHero(@RequestBody HeroCreationRequest request) {
        return heroCreatorApi
                .create(heroCreationToDomain(request))
                .map(HeroDtoMapper::toDto)
                .fold(ResponseEntity.badRequest()::body, ResponseEntity::ok);
    }
}