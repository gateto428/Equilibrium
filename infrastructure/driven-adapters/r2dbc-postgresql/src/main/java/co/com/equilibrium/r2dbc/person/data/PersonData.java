package co.com.equilibrium.r2dbc.person.data;

import co.com.equilibrium.model.commons.enums.RolType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("person")
public class PersonData implements Persistable<String> {
    @Id
    private String idPerson;
    private String namePerson;
    private String lastNamePerson;
    private LocalDate birthDatePerson;
    private String emailPerson;
    private  String phonePerson;
    private  String pass;
    private RolType rolType;
    private Boolean isActive;

    @Override
    public String getId() {
        return this.getIdPerson();
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
