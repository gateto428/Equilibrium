package co.com.equilibrium.model.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Course {
    private Integer idCourse;
    private String descriptionCourse;
    private String nameCourse;
    private String creatorCourse;
    private Boolean isActive;
}
