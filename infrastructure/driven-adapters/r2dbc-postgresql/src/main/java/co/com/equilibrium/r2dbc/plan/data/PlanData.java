package co.com.equilibrium.r2dbc.plan.data;

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
@Table("plan")
public class PlanData implements Persistable<String> {
    @Id
    private Integer idPlan;
    private String descriptionPlan;
    private String namePlan;
    private Double costPlan;
    private String creatorPlan;
    private Boolean isActive;
    private Integer classTake;
    @Override
    public String getId() {
        return this.idPlan.toString();
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
