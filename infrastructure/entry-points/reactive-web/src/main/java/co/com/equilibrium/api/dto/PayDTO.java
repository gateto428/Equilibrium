package co.com.equilibrium.api.dto;

import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.model.commons.enums.PayType;
import co.com.equilibrium.model.pay.Pay;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayDTO extends DTO<Pay> {
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private String idPerson;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Integer idPlan;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private LocalDate dateStart;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private LocalDate dateEnd;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private Integer classTake;
    @NotNull(message = "This no null", groups = {OnCreate.class, OnUpdate.class})
    private PayType payType;
    private String payNumber;

    @Override
    public Mono<Pay> toModel() {
        return Mono.just(Pay.builder()
                .idPerson(this.idPerson)
                .idPlan(this.idPlan)
                .dateStart(this.dateStart)
                .dateEnd(this.dateEnd)
                .classTake(this.classTake)
                .payType(this.payType)
                .payNumber(this.payNumber)
                .build());
    }
}
