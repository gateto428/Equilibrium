package co.com.equilibrium.r2dbc.course.data;

import co.com.equilibrium.model.course.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toEntity(CourseData courseData);

    CourseData toData(Course course);
}
