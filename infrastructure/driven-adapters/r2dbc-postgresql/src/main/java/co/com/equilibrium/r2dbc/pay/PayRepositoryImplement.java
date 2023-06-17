package co.com.equilibrium.r2dbc.pay;

import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.pay.Pay;
import co.com.equilibrium.model.pay.gateways.PayGateway;
import co.com.equilibrium.r2dbc.AdapterOperations;
import co.com.equilibrium.r2dbc.pay.data.PayData;
import co.com.equilibrium.r2dbc.pay.data.PayMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.*;

@Repository
public class PayRepositoryImplement extends AdapterOperations<Pay, PayData, String, PayRepository>
        implements PayGateway {

    public PayRepositoryImplement(PayRepository repository, PayMapper mapper) {
        super(repository, mapper::toData, mapper::toEntity);
    }


    @Override
    public Mono<Pay> savePay(Pay pay) {
        return repository.save(this.convertToData(pay))
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, SAVE_PAY_ERROR));
    }
}
