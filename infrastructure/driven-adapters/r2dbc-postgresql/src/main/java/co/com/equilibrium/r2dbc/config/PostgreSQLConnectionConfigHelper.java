package co.com.equilibrium.r2dbc.config;

import co.com.equilibrium.secretsmanager.SecretsManager;
import co.com.equilibrium.secretsmanager.SecretsNameStandard;

import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

@Configuration
@RequiredArgsConstructor
public class PostgreSQLConnectionConfigHelper {

	private final SecretsManager secretsManager;

	private final SecretsNameStandard secretsNameStandard;


	private PostgresqlConnectionProperties postgresqlProperties(){
		return secretsNameStandard.getSecretRds()
				.flatMap(secretName -> secretsManager.getSecret(secretName, PostgresqlConnectionProperties.class))
				.block();
	}

	@Bean
	public ConnectionFactoryOptions buildConnectionConfiguration(@Value("${adapters.postgresql.schema}") String schema,
																  @Value("${adapters.postgresql.pool.max}") Integer max) {
		PostgresqlConnectionProperties properties = postgresqlProperties();
		return ConnectionFactoryOptions.builder()
				.option(MAX_SIZE, max)
				.option(DRIVER, "pool")
				.option(PROTOCOL, "postgresql")
				.option(HOST, 	properties.getHost())
				.option(USER, properties.getUsername())
				.option(PASSWORD, properties.getPassword())
				.option(DATABASE, properties.getDbname())
				.option(Option.valueOf("sslmode"), "verify-full")
				.option(Option.valueOf("schema"), schema)
				.build();
	}
}
