package co.com.equilibrium.model.plancourses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PlanCourse {
    private Integer idPlan;
    private Integer idCourse;
    private Integer numberClass;
}
