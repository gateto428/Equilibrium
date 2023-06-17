package co.com.equilibrium.r2dbc.pay.data;

import co.com.equilibrium.model.commons.enums.PayType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("pay")
public class PayData implements Persistable<String> {
    @Id
    private String idPerson;
    private Integer idPlan;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private Integer classTake;
    private PayType payType;
    private String payNumber;
    @Override
    public String getId() {
        return this.idPerson;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
