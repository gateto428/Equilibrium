package co.com.equilibrium.model.plan;

import co.com.equilibrium.model.course.Course;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Plan {
    private Integer idPlan;
    private String descriptionPlan;
    private String namePlan;
    private Double costPlan;
    private String creatorPlan;
    private Boolean isActive;
    private Integer classTake;
    @With
    private List<Course> courseList;
}
