package fr.cleancode.org.server.postgres.adapter;

import fr.cleancode.org.domain.ApplicationError;
import fr.cleancode.org.domain.player.functional.model.Player;
import fr.cleancode.org.domain.player.ports.server.PlayerPersistenceSpi;
import fr.cleancode.org.server.postgres.mapper.PlayerEntityMapper;
import fr.cleancode.org.server.postgres.repository.PlayerRepository;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static io.vavr.API.Try;

@Service
@RequiredArgsConstructor
public class PlayerDatabaseAdapter implements PlayerPersistenceSpi {

    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public Either<ApplicationError, Player> save(Player player) {
        val entity = PlayerEntityMapper.fromDomain(player);
        return Try(() -> playerRepository.save(entity))
                .toEither()
                .mapLeft(throwable -> new ApplicationError("Unable to save player", null, player, throwable))
                .map(PlayerEntityMapper::toDomain);
    }
}