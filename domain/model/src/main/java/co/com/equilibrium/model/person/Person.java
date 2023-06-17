package co.com.equilibrium.model.person;

import co.com.equilibrium.model.commons.enums.RolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Person {
    private String idPerson;
    private String namePerson;
    private String lastNamePerson;
    private LocalDate birthDatePerson;
    private String emailPerson;
    private  String phonePerson;
    private  String pass;
    private RolType rolType;
    private Boolean isActive;
}
