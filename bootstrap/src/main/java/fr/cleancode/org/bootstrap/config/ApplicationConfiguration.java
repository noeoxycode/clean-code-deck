package fr.cleancode.org.bootstrap.config;

import fr.cleancode.org.bootstrap.config.domain.DomainConfiguration;
import fr.cleancode.org.server.mongo.config.MongoServerAdapterConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DomainConfiguration.class, MongoServerAdapterConfiguration.class})
public class ApplicationConfiguration {
}