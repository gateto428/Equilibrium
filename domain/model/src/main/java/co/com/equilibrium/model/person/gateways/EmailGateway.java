package co.com.equilibrium.model.person.gateways;

import co.com.equilibrium.model.person.Person;
import reactor.core.publisher.Mono;

public interface EmailGateway {
    Mono<Boolean> sendMessageWelcome(String name, String messageConstances, String emailRecipient, String subject);

    Mono<Boolean> sendMessageNotificationActivated(String name,String id, String email);
}
