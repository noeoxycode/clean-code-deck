package fr.cleancode.org.server.mongo.adapter;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import fr.cleancode.org.server.mongo.entities.PlayerEntity;
import fr.cleancode.org.server.mongo.exception.PlayerNotFoundException;
import fr.cleancode.org.server.mongo.mapper.PlayerEntityMapper;
import fr.cleancode.org.server.mongo.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerDatabaseAdapter implements PlayerFinderSpi, PlayerCreatorSpi {

    private final PlayerRepository playerRepository;

    @Override
    public Player save(Player player) {
        val entity = PlayerEntityMapper.fromDomain(player);
        playerRepository.save(entity);
        return PlayerEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Player> findPlayerById(UUID playerId) {
        Optional<PlayerEntity> player = playerRepository.findById(playerId);
        return Optional.ofNullable(player
                .map(PlayerEntityMapper::toDomain)
                .orElseThrow(
                        () -> new PlayerNotFoundException("Player with id : "
                                + playerId + " was not found !"))
        );
    }

    @Override
    public List<Player> findAllPlayers() {
        List<PlayerEntity> players = playerRepository.findAll();
        return PlayerEntityMapper.toDomainList(players);
    }
}