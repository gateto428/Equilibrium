package co.com.equilibrium.r2dbc.person;

import co.com.equilibrium.model.commons.enums.RolType;
import co.com.equilibrium.model.person.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PersonRepositoryImplementTest {
    @Autowired
    private PersonRepositoryImplement personRepositoryImplement;
    private final Person person = new Person();

    @BeforeEach
    public void init(){
        person.setIdPerson("3");
        person.setNamePerson("nio");
        person.setLastNamePerson("su");
        person.setBirthDatePerson(LocalDate.now());
        person.setEmailPerson("test54@gmail.com");
        person.setPhonePerson("4434433");
        person.setPass("jhahsgs");
        person.setIsActive(true);
        person.setRolType(RolType.ADMINISTRATOR);
    }

    @Test
    void savePersonTest(){
        person.setIdPerson("5");
        personRepositoryImplement.savePerson(person)
                .subscribe(p -> StepVerifier.create(personRepositoryImplement.findById(p))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    @Test
    void findByEmailTest(){
        personRepositoryImplement.findByEmail(person)
                .subscribe(p -> StepVerifier.create(personRepositoryImplement.findById(p))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    @Test
    void findByIdTest(){
        personRepositoryImplement.findById(person)
                .subscribe(p -> StepVerifier.create(personRepositoryImplement.findById(p))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    @Test
    void deactivatePersonTest(){
        assertThat(personRepositoryImplement.deactivatePerson(person))
                .isNotNull();
    }

    @Test
    void activatePersonTest(){
        assertThat(personRepositoryImplement.activatePerson(person))
                .isNotNull();
    }

    @Test
    void updatePersonTest(){
        person.setEmailPerson("123@gmail.com");
        assertThat(personRepositoryImplement.updatePerson(person))
                .isNotNull();
    }

    @Test
    void updatePasswordTest(){
        person.setEmailPerson("325541s@gmail.com");
        assertThat(personRepositoryImplement.updatePassword(person))
                .isNotNull();
    }
}
