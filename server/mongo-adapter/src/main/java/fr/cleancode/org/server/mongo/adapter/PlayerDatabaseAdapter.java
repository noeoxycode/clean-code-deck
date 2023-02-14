package fr.cleancode.org.server.postgres.adapter;

import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerPersistenceSpi;
import fr.cleancode.org.server.postgres.mapper.PlayerEntityMapper;
import fr.cleancode.org.server.postgres.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerDatabaseAdapter implements PlayerPersistenceSpi {

    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public Player save(Player player) {
        val entity = PlayerEntityMapper.fromDomain(player);
        playerRepository.save(entity);
        return PlayerEntityMapper.toDomain(entity);
    }
}