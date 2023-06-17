package co.com.equilibrium.model.classp;

import co.com.equilibrium.model.course.Course;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Class {
    private String idClass;
    private LocalDate dateClass;
    private LocalTime hourClass;
    private Integer durationClass;
    private Integer quotasClass;
    private String roomClass;
    private Integer idCourse;
    private String creatorClass;
    private String coachClass;
    @With
    private Course course;
}
