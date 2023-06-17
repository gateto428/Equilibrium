package co.com.equilibrium.r2dbc.person;

import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.person.gateways.PersonGateway;
import co.com.equilibrium.r2dbc.AdapterOperations;
import co.com.equilibrium.r2dbc.person.data.PersonData;
import co.com.equilibrium.r2dbc.person.data.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.*;

@Repository
public class PersonRepositoryImplement extends AdapterOperations<Person, PersonData, String, PersonRepository>
        implements PersonGateway {
    @Autowired
    public PersonRepositoryImplement(PersonRepository repository, PersonMapper mapper) {
        super(repository, mapper::toData, mapper::toEntity);
    }

    @Override
    public Mono<Person> savePerson(Person person) {
        return Mono.just(person)
                .map(this::convertToData)
                .flatMap(repository::save)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, SAVE_PERSON_ERROR));
    }

    @Override
    public Mono<Person> findByEmail(Person person) {
        return doQuery(repository.findByEmail(person.getEmailPerson()))
                .onErrorMap(e -> new TechnicalException(e, FIND_PERSON_ERROR));
    }

    @Override
    public Mono<Person> findById(Person person) {
        return doQuery(repository.findById(person.getIdPerson()))
                .onErrorMap(e -> new TechnicalException(e, FIND_PERSON_ERROR));
    }

    @Override
    public Mono<Map<String, String>> deactivatePerson(Person person) {
        return repository.deactivatePerson(person.getIdPerson())
                .onErrorMap(e -> new TechnicalException(e, ERROR_INACTIVATE_PERSON))
                .thenReturn(Map.of("Status", "Desactivado Persona ID: " + person.getIdPerson()));
    }

    @Override
    public Mono<Map<String, String>> activatePerson(Person person) {
        return repository.activatePerson(person.getIdPerson())
                .onErrorMap(e -> new TechnicalException(e, ERROR_INACTIVATE_PERSON))
                .thenReturn(Map.of("Status", "Activada Persona ID: " + person.getIdPerson()));
    }

    @Override
    public Mono<Map<String, String>> updatePerson(Person person) {
        return repository.updatePerson(person)
                .onErrorMap(e -> new TechnicalException(e, ERROR_INACTIVATE_PERSON))
                .thenReturn(Map.of("Status", "Informacion actualizada " + person.getIdPerson()));
    }

    @Override
    public Mono<Map<String, String>> updatePassword(Person person) {
        return repository.updatePassword(person)
                .onErrorMap(e -> new TechnicalException(e, ERROR_INACTIVATE_PERSON))
                .thenReturn(Map.of("Status", "Password Update"));
    }

    @Override
    public Flux<Person> findAll(int limit, int offset) {
        return repository.findAllPerson(limit, offset)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, FIND_PERSON_ERROR));
    }
}
