package co.com.equilibrium.api.dto;

import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.model.userclass.UserClass;
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
public class UserClassDTO extends DTO<UserClass> {

    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String studentId;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String idClass;
    @NotNull(message = "This no null", groups = {OnUpdate.class})
    private boolean isPresent;

    @Override
    public Mono<UserClass> toModel() {
        return Mono.just(UserClass.builder()
                .studentId(this.studentId)
                .idClass(this.idClass)
                .isPresent(this.isPresent)
                .build());
    }
}
