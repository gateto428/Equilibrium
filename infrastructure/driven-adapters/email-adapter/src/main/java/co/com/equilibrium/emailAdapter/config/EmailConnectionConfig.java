package co.com.equilibrium.emailAdapter.config;

import co.com.equilibrium.secretsmanager.SecretsManager;
import co.com.equilibrium.secretsmanager.SecretsNameStandard;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class EmailConnectionConfig {
    private final SecretsManager secretsManager;
    private final SecretsNameStandard secretsNameStandard;
    @Bean
    public EmailProperties emailProperties(){
        return secretsNameStandard.getSecretEmail()
                .flatMap(secretName -> secretsManager.getSecret(secretName, EmailProperties.class)).block();
    }

}
