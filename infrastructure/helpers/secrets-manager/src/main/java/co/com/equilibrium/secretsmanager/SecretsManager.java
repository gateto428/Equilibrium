package co.com.equilibrium.secretsmanager;

import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnectorAsync;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.SECRET_EXCEPTION;

@Component
@RequiredArgsConstructor
@Log
public class SecretsManager {
    private final AWSSecretManagerConnectorAsync awsSecretManagerConnectorAsync;

    public <T> Mono<T> getSecret(String secret, Class<T> cls){
        return awsSecretManagerConnectorAsync.getSecret(secret, cls)
                .doOnSuccess(e -> log.log(Level.INFO, String.format("Scret %S was obtain sucessfully", secret)))
                .onErrorMap(error -> {
                    String message = String.join(" ", secret, error.getMessage());
                    return new TechnicalException(message, SECRET_EXCEPTION);
                });
    }
}
