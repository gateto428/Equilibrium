package co.com.equilibrium.model.person.gateways;

import co.com.equilibrium.model.person.Person;
import reactor.core.publisher.Mono;


public interface JWTGateway {
    String getToken(Person person);
    Mono<String> getKeyEncrypt(String pass);
    Mono<String> getKeyDecrypt(String pass);
    boolean verifyToken(String token);
}
