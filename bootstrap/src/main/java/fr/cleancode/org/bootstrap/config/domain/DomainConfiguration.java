package fr.cleancode.org.bootstrap.config.domain;

import fr.cleancode.org.domain.fight.functional.service.FightService;
import fr.cleancode.org.domain.fight.port.client.FightApi;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
import fr.cleancode.org.domain.hero.functional.service.HeroCreatorService;
import fr.cleancode.org.domain.hero.functional.service.HeroFinderService;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.hero.ports.server.HeroCreatorSpi;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import fr.cleancode.org.domain.player.functional.service.PlayerCreatorService;
import fr.cleancode.org.domain.player.functional.service.PlayerFinderService;
import fr.cleancode.org.domain.player.ports.client.PlayerCreatorApi;
import fr.cleancode.org.domain.player.ports.client.PlayerFinderApi;
import fr.cleancode.org.domain.player.ports.server.PlayerCreatorSpi;
import fr.cleancode.org.domain.player.ports.server.PlayerFinderSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public HeroCreatorApi heroCreatorService(HeroCreatorSpi spi) {
        return new HeroCreatorService(spi);
    }

    @Bean
    public HeroFinderApi heroFinderService(HeroFinderSpi spi, PlayerFinderSpi playerFinderSpi) {
        return new HeroFinderService(spi, playerFinderSpi);
    }

    @Bean
    public PlayerCreatorApi playerCreatorService(PlayerCreatorSpi spi) {
        return new PlayerCreatorService(spi);
    }

    @Bean
    public PlayerFinderApi playerFinderService(PlayerFinderSpi spi) {
        return new PlayerFinderService(spi);
    }

    @Bean
    public FightApi fightService(HeroFinderApi heroFinderApi, PlayerFinderApi playerFinderApi, PlayerCreatorSpi playerCreatorSpi, FightCreatorSpi fightCreatorSpi){ return new FightService(heroFinderApi, playerFinderApi, playerCreatorSpi, fightCreatorSpi);}
}