package co.com.equilibrium.usecase.Person;

import co.com.equilibrium.model.commons.constnces.MessageConstances;
import co.com.equilibrium.model.commons.exceptions.BussinessException;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.person.gateways.EmailGateway;
import co.com.equilibrium.model.person.gateways.JWTGateway;
import co.com.equilibrium.model.person.gateways.PersonGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.Map;

import static co.com.equilibrium.model.commons.enums.BusinessErrorMessage.*;

@RequiredArgsConstructor
public class PersonUseCase {
    private final PersonGateway personGateway;
    private final JWTGateway jwtGateway;

    private final EmailGateway emailGateway;

    public boolean validateToken(String token){
        return jwtGateway.verifyToken(token);
    }

    public Mono<Person> savePerson(Person person) {
        return Mono.just(person)
                .map(ps -> {
                    String pass = jwtGateway.getKeyEncrypt(ps.getPass()).block();
                    ps.setPass(pass);
                    return ps;
                }).flatMap(ps -> personGateway.savePerson(ps))
                .map(ps -> {
                    if(ps.getIsActive()) emailGateway.sendMessageWelcome(ps.getNamePerson(),
                            MessageConstances.WELCOME_MESSAGE_ACTIVE, ps.getEmailPerson(), MessageConstances.SUBJECT_WELCOME);
                    else{
                        emailGateway.sendMessageWelcome(ps.getNamePerson(),
                                MessageConstances.WELCOME_MESSAGE_INACTIVE, ps.getEmailPerson(), MessageConstances.SUBJECT_WELCOME);
                        emailGateway.sendMessageNotificationActivated("Nicolas", ps.getIdPerson(), ps.getEmailPerson());
                    }
                    return ps;
                })
                .map(ps -> ps.toBuilder()
                        .pass(null)
                        .build());
    }

    public Mono<Object> loginPerson(Person person) {
        return Mono.just(person)
                .flatMap(ps -> personGateway.findByEmail(person))
                .switchIfEmpty(Mono.error(new BussinessException(PERSON_NOT_FOUND_OR_INACTIVE)))
                .map(ps -> {
                    ps.setPass(jwtGateway.getKeyDecrypt(ps.getPass()).block());
                    return ps;
                })
                .flatMap(ps ->  ps.getPass().equals(person.getPass()) ? Mono.just(ps) : Mono.empty())
                .switchIfEmpty(Mono.error(new BussinessException(INVALID_CREDENTIALS)))
                .map(ps -> ps.toBuilder()
                        .pass(jwtGateway.getToken(ps))
                        .build());
    }

    public Mono<Person> findById(Person person) {
        return personGateway.findById(person)
                .switchIfEmpty(Mono.error(new BussinessException(PERSON_NOT_FOUND)));
    }

    public Mono<Map<String, String>> deactivatePerson(Person person){
        return personGateway.findById(person)
                .switchIfEmpty(Mono.error(new BussinessException(PERSON_NOT_FOUND)))
                .flatMap(personGateway::deactivatePerson);
    }

    public Mono<Map<String, String>> activatePerson(Person person){
        return personGateway.findById(person)
                .switchIfEmpty(Mono.error(new BussinessException(PERSON_NOT_FOUND)))
                .flatMap(personGateway::activatePerson);
    }

    public Mono<Map<String, String>> updatePerson(Person person) {
        return personGateway.findById(person)
                .switchIfEmpty(Mono.error(new BussinessException(PERSON_NOT_FOUND)))
                .flatMap(ps -> personGateway.updatePerson(person));
    }

    public Mono<Map<String, String>> updatePassword(Person person) {
        return personGateway.findByEmail(person)
                .switchIfEmpty(Mono.error(new BussinessException(PERSON_NOT_FOUND)))
                .flatMap(ps -> jwtGateway.getKeyEncrypt(person.getPass()))
                .flatMap(key -> personGateway.updatePassword(Person.builder()
                                .emailPerson(person.getEmailPerson())
                                .pass(key)
                        .build()));
    }

    public Mono<List<Person>> findAll(Tuple2<String, String> objects) {
        return personGateway.findAll(Integer.parseInt(objects.getT1()), Integer.parseInt(objects.getT2()))
                .collectList();
    }
}
