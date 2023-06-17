package co.com.equilibrium.r2dbc.person;

import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.r2dbc.person.data.PersonData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface PersonRepository extends ReactiveCrudRepository<PersonData, String> {

    @Query("SELECT * FROM person WHERE email_person = $1 AND is_active = True;")
    Mono<PersonData> findByEmail(String email);

    @Query("SELECT id_person, name_person, last_name_person, birth_date_person, email_person, phone_person," +
            " is_active, rol_type FROM person WHERE id_person = $1;")
    Mono<PersonData> findById(String id);

    @Query("UPDATE person SET is_active = false WHERE id_person = $1;")
    Mono<PersonData> deactivatePerson(String idPerson);

    @Query("UPDATE person SET is_active = true WHERE id_person = $1;")
    Mono<PersonData> activatePerson(String idPerson);

    @Query("UPDATE person SET name_person=:#{#p.namePerson}, last_name_person=:#{#p.lastNamePerson}, " +
            "birth_date_person=:#{#p.birthDatePerson}, email_person=:#{#p.emailPerson}, phone_person=:#{#p.phonePerson}, " +
            "is_active=:#{#p.isActive}, rol_type=:#{#p.rolType} WHERE id_person=:#{#p.idPerson};")
    Mono<PersonData> updatePerson(@Param("p") Person person);

    @Query("UPDATE person SET pass=:#{#p.pass} WHERE email_person =:#{#p.emailPerson};")
    Mono<PersonData> updatePassword(@Param("p")Person person);

    @Query("SELECT id_person, name_person, last_name_person, birth_date_person, email_person, phone_person," +
            " is_active, rol_type FROM person ORDER BY id_person DESC OFFSET $2 LIMIT $1;")
    Flux<PersonData> findAllPerson(int limit,  int offset);
}
