package co.com.equilibrium.r2dbc.plancourse.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("plan_courses")
public class PlanCourseData implements Persistable<Integer> {
    @Id
    private Integer idPlan;
    private Integer idCourse;
    private Integer numberClass;
    @Override
    public Integer getId() {
        return this.idPlan;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
