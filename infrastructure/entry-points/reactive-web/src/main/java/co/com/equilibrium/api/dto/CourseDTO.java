package co.com.equilibrium.api.dto;

import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.model.course.Course;
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
public class CourseDTO extends DTO<Course> {
    @NotNull(message = "This no null", groups = {OnUpdate.class})
    private Integer idCourse;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String descriptionCourse;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String nameCourse;
    @NotNull(message = "This no null", groups = {OnCreate.class})
    private String creatorCourse;
    @NotNull(message = "This no null", groups = {OnCreate.class})
    private Boolean isActive;

    @Override
    public Mono<Course> toModel() {
        return Mono.just(
                Course.builder()
                        .idCourse(this.idCourse)
                        .descriptionCourse(this.descriptionCourse)
                        .nameCourse(this.nameCourse)
                        .creatorCourse(this.creatorCourse)
                        .isActive(this.isActive)
                        .build()
        );
    }
}
