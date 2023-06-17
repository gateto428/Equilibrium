package co.com.equilibrium.api.dto;

import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.model.classp.Class;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassDTO extends DTO<Class> {
    @NotNull(message = "This no null", groups = {OnUpdate.class})
    private String idClass;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private LocalDate dateClass;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private LocalTime hourClass;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Integer durationClass;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Integer quotasClass;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String roomClass;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Integer idCourse;
    @NotNull(message = "This no null", groups = {OnCreate.class})
    private String creatorClass;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String coachClass;

    @Override
    public Mono<Class> toModel() {
        return Mono.just(Class.builder()
                .idClass(this.idClass)
                .dateClass(this.dateClass)
                .hourClass(this.hourClass)
                .durationClass(this.durationClass)
                .quotasClass(this.quotasClass)
                .roomClass(this.roomClass)
                .idCourse(this.idCourse)
                .creatorClass(this.creatorClass)
                .coachClass(this.coachClass)
                .build());
    }
}
