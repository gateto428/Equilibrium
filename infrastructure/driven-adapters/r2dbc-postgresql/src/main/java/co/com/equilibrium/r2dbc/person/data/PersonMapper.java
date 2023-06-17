package co.com.equilibrium.r2dbc.person.data;

import co.com.equilibrium.model.person.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    Person toEntity(PersonData personData);

    PersonData toData(Person person);

}
