package co.com.equilibrium.r2dbc.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
@RequiredArgsConstructor
public class PostgreSQLAdapterConfig {

    private final ConnectionFactoryOptions options;

    @Bean("initializer")
    public ConnectionFactory initializer() {
        return ConnectionFactories.get(options);
    }
}
