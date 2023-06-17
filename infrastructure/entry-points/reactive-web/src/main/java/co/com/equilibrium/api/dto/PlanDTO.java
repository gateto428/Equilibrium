package co.com.equilibrium.api.dto;

import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.model.plan.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanDTO extends DTO<Plan> {
    @NotNull(message = "This no null", groups = {OnUpdate.class})
    private Integer idPlan;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String descriptionPlan;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String namePlan;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Double costPlan;
    @NotNull(message = "This no null", groups = {OnCreate.class})
    private String creatorPlan;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Boolean isActive;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Integer classTake;

    @Override
    public Mono<Plan> toModel() {
        return Mono.just(Plan.builder()
                .idPlan(this.idPlan)
                .descriptionPlan(this.descriptionPlan)
                .namePlan(this.namePlan)
                .costPlan(this.costPlan)
                .creatorPlan(this.creatorPlan)
                .isActive(this.isActive)
                .classTake(this.classTake)
                .build());
    }
}
