package co.com.equilibrium.secretmanager;

import co.com.equilibrium.secretsmanager.SecretsManagerConfig;
import co.com.equilibrium.secretsmanager.SecretsManagerProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class SecretsManagerConfigTest  {
    @InjectMocks
    private SecretsManagerConfig config;
    @Mock
    private SecretsManagerProperties properties;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        when(properties.getCacheTime()).thenReturn(3600);
        when(properties.getCacheSize()).thenReturn(300);
    }

    @Test
    void beanConnectionSecretManagerTest(){
        assertThat(config.ConnectionAws()).isNotNull();
    }

   /** @Test
    void beanConnectionLocalSecretManagerTest(){
        assertThat(config.ConnectionLocal("http://localhost:4566"))
                .isNotNull();
    }*/
}
