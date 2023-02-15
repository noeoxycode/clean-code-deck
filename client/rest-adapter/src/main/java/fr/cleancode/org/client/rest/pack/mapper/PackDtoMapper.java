package fr.cleancode.org.client.rest.pack.mapper;

import fr.cleancode.org.client.rest.hero.dto.HeroCreationRequest;
import fr.cleancode.org.client.rest.hero.dto.HeroDto;
import fr.cleancode.org.client.rest.hero.mapper.HeroDtoMapper;
import fr.cleancode.org.client.rest.pack.dto.PackCreationRequest;
import fr.cleancode.org.domain.hero.functional.model.Hero;
import fr.cleancode.org.domain.pack.functional.model.Pack;
import fr.cleancode.org.domain.pack.functional.model.PackType;

import java.util.List;
import java.util.stream.Collectors;

public interface PackDtoMapper {
    static Pack packCreationToDomain(PackCreationRequest dto) {
        Pack pack = null;
        switch (dto.packType()) {
            case SILVER -> {
                pack = PackType.SILVER.value;
            }
            case DIAMOND -> {
                pack = PackType.DIAMOND.value;
            }
        }
        return pack;
    }
}
