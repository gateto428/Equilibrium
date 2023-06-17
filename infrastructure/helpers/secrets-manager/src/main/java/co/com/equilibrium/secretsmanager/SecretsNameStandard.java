package co.com.equilibrium.secretsmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "adapters.secrets-manager")
public class SecretsNameStandard {
    private String secretRds;
    private String secretEmail;

    public Mono<String> getSecretRds() {
        return Mono.just(secretRds);
    }

    public Mono<String> getSecretEmail() {
        return Mono.just(secretEmail);
    }
}
