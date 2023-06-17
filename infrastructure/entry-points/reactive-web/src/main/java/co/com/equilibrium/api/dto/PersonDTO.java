package co.com.equilibrium.api.dto;

import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnLogin;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.model.commons.enums.RolType;
import co.com.equilibrium.model.person.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO extends DTO<Person> {

    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String idPerson;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String namePerson;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String lastNamePerson;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private LocalDate birthDatePerson;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnLogin.class, OnUpdate.class})
    private String emailPerson;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String phonePerson;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnLogin.class})
    private String pass;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private RolType rolType;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Boolean isActive;

    @Override
    public Mono<Person> toModel() {
        return Mono.just(Person.builder()
                .idPerson(this.idPerson)
                .namePerson(this.namePerson)
                .lastNamePerson(this.lastNamePerson)
                .birthDatePerson(this.birthDatePerson)
                .emailPerson(this.emailPerson)
                .phonePerson(this.phonePerson)
                .pass(this.pass)
                .rolType(this.rolType)
                .isActive(this.isActive)
                .build());
    }
}
