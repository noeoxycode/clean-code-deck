package fr.cleancode.org.bootstrap.config.domain;

import fr.cleancode.org.domain.hero.functional.service.HeroCreatorService;
import fr.cleancode.org.domain.hero.functional.service.HeroFinderService;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.hero.ports.server.HeroPersistenceSpi;
import fr.cleancode.org.domain.player.functional.service.PlayerCreatorService;
import fr.cleancode.org.domain.player.functional.service.PlayerFinderService;
import fr.cleancode.org.domain.player.ports.client.PlayerCreatorApi;
import fr.cleancode.org.domain.player.ports.client.PlayerFinderApi;
import fr.cleancode.org.domain.player.ports.server.PlayerPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public HeroCreatorApi heroCreatorService(HeroPersistenceSpi spi) {
        return new HeroCreatorService(spi);
    }

    @Bean
    public HeroFinderApi heroFinderService(HeroPersistenceSpi spi) {
        return new HeroFinderService(spi);
    }

    @Bean
    public PlayerCreatorApi playerCreatorService(PlayerPersistenceSpi spi) {
        return new PlayerCreatorService(spi);
    }

    @Bean
    public PlayerFinderApi playerFinderService(PlayerPersistenceSpi spi) {
        return new PlayerFinderService(spi);
    }
}