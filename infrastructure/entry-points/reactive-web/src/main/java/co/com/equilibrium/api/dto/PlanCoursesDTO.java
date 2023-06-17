package co.com.equilibrium.api.dto;

import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnDelete;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.model.plancourses.PlanCourse;
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
public class PlanCoursesDTO extends DTO<PlanCourse> {
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class, OnDelete.class})
    private Integer idPlan;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class, OnDelete.class})
    private Integer idCourse;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Integer numberClass;

    @Override
    public Mono<PlanCourse> toModel() {
        return Mono.just(PlanCourse.builder()
                .idPlan(this.idPlan)
                .idCourse(this.idCourse)
                .numberClass(this.numberClass)
                .build());
    }
}
