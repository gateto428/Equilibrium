package co.com.equilibrium.model.pay;

import co.com.equilibrium.model.commons.enums.PayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Pay {
    private String idPerson;
    private Integer idPlan;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer classTake;
    private PayType payType;
    private String payNumber;
}
