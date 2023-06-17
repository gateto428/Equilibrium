package co.com.equilibrium.model.person.gateways;

import co.com.equilibrium.model.person.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface PersonGateway {
    Mono<Person> savePerson(Person person);

    Mono<Person> findByEmail(Person person);

    Mono<Person> findById(Person person);

    Mono<Map<String, String>> deactivatePerson(Person person);

    Mono<Map<String, String>> activatePerson(Person person);

    Mono<Map<String, String>> updatePerson(Person person);
    Mono<Map<String, String>> updatePassword(Person person);
    Flux<Person> findAll(int limit, int offset);
}
