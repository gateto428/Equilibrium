package co.com.equilibrium.secretsmanager;

import co.com.bancolombia.secretsmanager.config.AWSSecretsManagerConfig;
import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnectorAsync;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
public class SecretsManagerConfig {
    private final SecretsManagerProperties properties;

    @Bean
    //@Profile({"dev", "qa", "pdn"})
    public AWSSecretManagerConnectorAsync ConnectionAws() {
        return new AWSSecretManagerConnectorAsync(AWSSecretsManagerConfig.builder()
                .cacheSeconds(properties.getCacheTime())
                .cacheSize(properties.getCacheSize())
                .build());
    }

    /**@Bean
    @Profile("local")
    public AWSSecretManagerConnectorAsync ConnectionLocal(@Value("${adapters.secrets-manager.endpoint}") String endpoint) {
        return new AWSSecretManagerConnectorAsync(AWSSecretsManagerConfig.builder()
                .endpoint(endpoint)
                .build());
    }*/
}
