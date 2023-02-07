package fr.cleancode.org.bootstrap.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.cleancode.org.bootstrap.config.domain.DomainConfiguration;
import fr.cleancode.org.server.postgres.config.PostgresServerAdapterConfiguration;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DomainConfiguration.class, PostgresServerAdapterConfiguration.class})
public class ApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new VavrModule());
    }
}