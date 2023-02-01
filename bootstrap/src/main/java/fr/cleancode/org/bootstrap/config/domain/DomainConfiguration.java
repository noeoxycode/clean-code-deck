package fr.cleancode.org.bootstrap.config.domain;

import fr.cleancode.org.domain.functional.service.DrivingLicenceCreatorService;
import fr.cleancode.org.domain.functional.service.TrafficOffenceAppenderService;
import fr.cleancode.org.domain.ports.client.DrivingLicenceCreatorApi;
import fr.cleancode.org.domain.ports.client.TrafficOffenceAppenderApi;
import fr.cleancode.org.domain.ports.server.DrivingLicencePersistenceSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

  @Bean
  public DrivingLicenceCreatorApi drivingLicenceCreatorService(DrivingLicencePersistenceSpi spi) {
    return new DrivingLicenceCreatorService(spi);
  }

  @Bean
  public TrafficOffenceAppenderApi trafficOffenceAppenderService(DrivingLicencePersistenceSpi spi) {
    return new TrafficOffenceAppenderService(spi);
  }
}
