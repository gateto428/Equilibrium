package co.com.equilibrium.secretsmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "adapters.secrets-manager")
public class SecretsManagerProperties {
    private int cacheSize;
    private int cacheTime;
    private String endpoint;
}
