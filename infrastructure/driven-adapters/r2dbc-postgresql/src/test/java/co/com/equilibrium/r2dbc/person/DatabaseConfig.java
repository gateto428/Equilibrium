package co.com.equilibrium.r2dbc.person;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.transaction.ReactiveTransactionManager;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {
    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactoryBuilder.withUrl("r2dbc:h2:mem:///testDbPerson?options=DB_CLOSE_DELAY=-1").build();
    }

    @Bean
    ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        CompositeDatabasePopulator populate = new CompositeDatabasePopulator();
        populate.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        populate.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("data-test.sql")));
        initializer.setDatabasePopulator(populate);
        return initializer;
    }
}