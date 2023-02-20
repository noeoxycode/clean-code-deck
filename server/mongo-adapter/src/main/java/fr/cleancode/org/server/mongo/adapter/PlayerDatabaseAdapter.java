package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerUpdateSpi;
import fr.cleancode.org.server.mongo.mapper.PlayerEntityMapper;
import fr.cleancode.org.server.mongo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerDatabaseAdapter implements PlayerFinderSpi, PlayerCreatorSpi, PlayerUpdateSpi {

    private final PlayerRepository playerRepository;

    @Override
    public Player create(Player player) {
        val entity = PlayerEntityMapper.fromDomain(player);
        playerRepository.save(entity);
        return PlayerEntityMapper.toDomain(entity);
    }

    @Override
    public Player update(Player player) {
        val entity = PlayerEntityMapper.fromDomain(player);
        playerRepository.save(entity);
        return PlayerEntityMapper.toDomain(entity);
    }

    @Override
    public Player findPlayerById(UUID playerId) {
        return PlayerEntityMapper
                .toDomain(playerRepository
                        .findPlayerEntitiesByPlayerId(playerId));
    }

    @Override
    public List<Player> findAllPlayers() {
        return PlayerEntityMapper.toDomainList(playerRepository.findAll());
    }
}