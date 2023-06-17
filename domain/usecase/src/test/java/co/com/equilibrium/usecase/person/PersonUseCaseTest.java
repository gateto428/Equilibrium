package co.com.equilibrium.usecase.person;

import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.person.gateways.EmailGateway;
import co.com.equilibrium.model.person.gateways.JWTGateway;
import co.com.equilibrium.model.person.gateways.PersonGateway;
import co.com.equilibrium.usecase.Person.PersonUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PersonUseCaseTest {
    @InjectMocks
    private PersonUseCase useCase;
    @Mock
    private JWTGateway jwtGateway;
    @Mock
    private EmailGateway emailGateway;
    @Mock
    private PersonGateway personGateway;
    private final Person person = new Person();

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        person.setIdPerson("1");
        person.setPass("2jmk3218");
        person.setEmailPerson("2jmk3218@gmail.com");
        person.setIsActive(true);
    }
    @Test
    void validateTokenTest(){
        when(jwtGateway.verifyToken(anyString()))
                .thenReturn(true);

        assertThat(useCase.validateToken(anyString())).isTrue();
    }

    @Test
    void savePersonTest(){
        when(jwtGateway.getKeyDecrypt(anyString()))
                .thenReturn(Mono.just("2jmk3218"));

        when(personGateway.savePerson(person))
                .thenReturn(Mono.just(person));

        assertThat(useCase.savePerson(person)).isNotNull();
    }

    @Test
    void loginPersonTest(){
       when(personGateway.findById(any()))
               .thenReturn(Mono.just(person));

       when(jwtGateway.getKeyDecrypt(anyString()))
               .thenReturn(Mono.just("2jmk3218"));

       when(jwtGateway.getToken(person)).thenReturn("token");

        assertThat(useCase.loginPerson(person)).isNotNull();
    }

    @Test
    void findByIdTest(){
        when(personGateway.findById(any()))
                .thenReturn(Mono.just(person));

        assertThat(useCase.findById(person)).isNotNull();
    }

    @Test
    void deactivatePerson(){
        when(personGateway.findById(any()))
                .thenReturn(Mono.just(person));

        when(personGateway.deactivatePerson(person))
                .thenReturn(Mono.just(Map.of("status", "ok")));

        assertThat(useCase.deactivatePerson(person)).isNotNull();
    }

    @Test
    void activatePersonTest(){
        when(personGateway.findById(any()))
                .thenReturn(Mono.just(person));

        when(personGateway.activatePerson(person))
                .thenReturn(Mono.just(Map.of("status", "ok")));

        assertThat(useCase.activatePerson(person)).isNotNull();
    }

    @Test
    void updatePersonTest(){
        when(personGateway.findById(any()))
                .thenReturn(Mono.just(person));

        when(personGateway.updatePerson(person))
                .thenReturn(Mono.just(Map.of("status", "ok")));

        assertThat(useCase.updatePerson(person)).isNotNull();
    }

    @Test
    void updatePasswordTest(){
        when(personGateway.findByEmail(any()))
                .thenReturn(Mono.just(person));

        when(jwtGateway.getKeyEncrypt(anyString()))
                .thenReturn(Mono.just("key"));

        when(personGateway.updatePassword(person))
                .thenReturn(Mono.just(Map.of("status", "ok")));

        assertThat(useCase.updatePassword(person)).isNotNull();
    }
}
