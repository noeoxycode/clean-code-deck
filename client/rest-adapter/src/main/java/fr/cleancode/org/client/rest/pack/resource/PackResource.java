package fr.cleancode.org.client.rest.pack.resource;

import fr.cleancode.org.client.rest.hero.dto.HeroDto;
import fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper;
import fr.cleancode.org.client.rest.pack.dto.PackCreationRequest;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.pack.ports.client.BuyPackApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/buyPack/{playerId}")
public class PackResource {

    private final BuyPackApi buyPackApi;

    @GetMapping
    public List<HeroDto> buyPack(@PathVariable UUID playerId, @RequestBody PackCreationRequest request) {
        List<Hero> packContent = buyPackApi.buyPack(playerId,request.packType().value);
        return HeroDtoMapper.toDtoList(packContent);
    }
}