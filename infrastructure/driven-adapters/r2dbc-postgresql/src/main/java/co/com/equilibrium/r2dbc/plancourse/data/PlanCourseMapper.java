package co.com.equilibrium.r2dbc.plancourse.data;

import co.com.equilibrium.model.plancourses.PlanCourse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlanCourseMapper {
    PlanCourse toEntity(PlanCourseData planData);
    PlanCourseData toData(PlanCourse plan);
}
