package co.com.equilibrium.r2dbc.config;

import co.com.equilibrium.secretsmanager.SecretsManager;
import co.com.equilibrium.secretsmanager.SecretsNameStandard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostgreSQLConnectionConfigHelperTest {
    private static final String secretName = "secret-example";
    public static final String host = "example.com";
    public static final String database = "database-name";
    public static final String schema = "schema";
    public static final String username = "user2";
    public static final String password = "pass2";
    public static final Integer port = 5432;
    @InjectMocks
    private PostgreSQLConnectionConfigHelper helper;
    public final PostgresqlConnectionProperties properties = new PostgresqlConnectionProperties();
    @Mock
    private SecretsNameStandard secretsNameStandard;
    @Mock
    private SecretsManager secretsManager;

    @BeforeEach
    public void init() {
        properties.setHost(host);
        properties.setDbname(database);
        properties.setSchema(schema);
        properties.setUsername(username);
        properties.setPassword(password);
        properties.setPort(port);
        when(secretsManager.getSecret(secretName, PostgresqlConnectionProperties.class)).thenReturn(Mono.just(properties));
        when(secretsNameStandard.getSecretRds()).thenReturn(Mono.just(secretName));
    }

    @Test
    void postgresqlProperties(){
        assertThat(helper.buildConnectionConfiguration("prueba", 1)).isNotNull();
    }
}

