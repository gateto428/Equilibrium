package co.com.equilibrium.r2dbc.classp.data;

import co.com.equilibrium.model.classp.Class;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassMapper {
    Class toEntity(ClassData classData);

    ClassData toData(Class classp);
}
