package co.com.equilibrium.secretmanager;

import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnectorAsync;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.secretsmanager.SecretsManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static reactor.core.publisher.Mono.just;
import static org.mockito.Mockito.when;

class SecretManagerTest {
    private static final String SECRET = "any-secret";

    @InjectMocks
    private SecretsManager secretsManager;
    @Mock
    private AWSSecretManagerConnectorAsync connectorAsync;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSecretWhenExistSecretThenReturnObject() {
        when(connectorAsync.getSecret(SECRET, ClassTest.class))
                .thenReturn(just(ClassTest.builder().host("localhost").build()));
        StepVerifier.create(secretsManager.getSecret(SECRET, ClassTest.class))
                .expectNextMatches(classTest -> classTest.getHost().equals("localhost")).verifyComplete();
    }

    @Test
    void getSecretWhenExistSecretThenReturnError() {
        when(connectorAsync.getSecret(SECRET, ClassTest.class)).thenReturn(Mono.error(new RuntimeException("error")));
        StepVerifier.create(secretsManager.getSecret(SECRET, ClassTest.class)).expectErrorMatches(e -> e instanceof TechnicalException).verify();
    }

    @Data
    @Builder
    @AllArgsConstructor
    private static class ClassTest {
        private String dbname;
        private String schema;
        private String username;
        private String password;
        private String host;
        private Integer port;
    }
}
