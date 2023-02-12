package fr.cleancode.org.bootstrap.config.domain;

import fr.cleancode.org.domain.functional.service.HeroCreatorService;
import fr.cleancode.org.domain.functional.service.HeroFinderService;
import fr.cleancode.org.domain.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.ports.server.HeroPersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public HeroCreatorApi heroCreatorService(HeroPersistenceSpi spi) {
        return new HeroCreatorService(spi);
    }

    @Bean
    public HeroFinderApi heroFinderService(HeroPersistenceSpi spi) { return new HeroFinderService(spi); }
}