package fr.cleancode.org.server.mongo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EnableAutoConfiguration
@EnableTransactionManagement
@EntityScan(basePackages = "fr.cleancode.org.server.mongo.entities")
@EnableJpaRepositories(basePackages = "fr.cleancode.org.server.mongo.repository")
public class PostgresServerAdapterConfiguration {
}