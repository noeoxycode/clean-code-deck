package fr.cleancode.org.bootstrap.config.domain;

import fr.cleancode.org.domain.fight.functional.service.FightActionsService;
import fr.cleancode.org.domain.fight.functional.service.FightHistoryFinderService;
import fr.cleancode.org.domain.fight.functional.service.FightService;
import fr.cleancode.org.domain.fight.functional.service.UpdateAfterFightService;
import fr.cleancode.org.domain.fight.port.client.FightCreatorApi;
import fr.cleancode.org.domain.fight.port.client.FightFinderApi;
import fr.cleancode.org.domain.fight.port.server.FightCreatorSpi;
import fr.cleancode.org.domain.fight.port.server.FightFinderSpi;
import fr.cleancode.org.domain.hero.functional.service.HeroCreatorService;
import fr.cleancode.org.domain.hero.functional.service.HeroFinderService;
import fr.cleancode.org.domain.hero.ports.client.HeroCreatorApi;
import fr.cleancode.org.domain.hero.ports.client.HeroFinderApi;
import fr.cleancode.org.domain.hero.ports.server.HeroCreatorSpi;
import fr.cleancode.org.domain.hero.ports.server.HeroFinderSpi;
import fr.cleancode.org.domain.money.EarningTokenService;
import fr.cleancode.org.domain.pack.functional.service.OpenPackService;
import fr.cleancode.org.domain.pack.functional.service.PackContentService;
import fr.cleancode.org.domain.pack.functional.service.generator.PackContentGeneratorService;
import fr.cleancode.org.domain.pack.ports.client.OpenPackApi;
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
    public HeroFinderApi heroFinderService(HeroFinderSpi heroFinderSpi, PlayerFinderSpi playerFinderSpi) {
        return new HeroFinderService(heroFinderSpi, playerFinderSpi);
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
    public OpenPackApi openPackService(
            PlayerFinderSpi playerFinderSpi,
            PlayerCreatorSpi playerCreatorSpi,
            HeroFinderSpi heroFinderSpi
    ) {
        PackContentGeneratorService packContentGeneratorService =
                new PackContentGeneratorService(heroFinderSpi);
        PackContentService packContentService =
                new PackContentService(packContentGeneratorService);

        return new OpenPackService(playerFinderSpi,
                playerCreatorSpi,
                packContentService);
    }

    @Bean
    public FightCreatorApi fightService(PlayerFinderSpi playerFinderApi,
                                        FightCreatorSpi fightCreatorSpi,
                                        PlayerCreatorSpi playerCreatorSpi,
                                        FightFinderSpi fightFinderSpi,
                                        HeroFinderSpi heroFinderSpi) {
        HeroFinderService heroFinderService = new HeroFinderService(heroFinderSpi, playerFinderApi);
        FightActionsService fightUtilsService = new FightActionsService();
        EarningTokenService earningTokenService = new EarningTokenService(fightFinderSpi);
        UpdateAfterFightService updateAfterFightService = new UpdateAfterFightService(earningTokenService);
        return new FightService(heroFinderService, playerFinderApi, playerCreatorSpi, fightCreatorSpi, fightUtilsService, updateAfterFightService);
    }

    @Bean
    public FightFinderApi fightFinderApiService(FightFinderSpi fightFinderSpi) {
        return new FightHistoryFinderService(fightFinderSpi);
    }
}