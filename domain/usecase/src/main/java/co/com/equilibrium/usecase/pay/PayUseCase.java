package co.com.equilibrium.usecase.pay;

import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.pay.Pay;
import co.com.equilibrium.model.pay.gateways.PayGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.LocalDate;

import static co.com.equilibrium.model.commons.enums.PayType.CARD;
import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.TRANSACTION_REJECT;

@RequiredArgsConstructor
public class PayUseCase {
    private final PayGateway payGateway;

    public Mono<Pay> savePay(Pay pay) {
        return payGateway.savePay(pay);
    }

    private LocalDate generateDateEnd() {
        return LocalDate.now().plusMonths(1);
    }

    public Mono<Pay> payEpayco(Tuple2<String, Pay> objects) {
        return Mono.just(objects)
                .filter(tuple -> tuple.getT1().equals("1"))
                .switchIfEmpty(Mono.error(new TechnicalException(TRANSACTION_REJECT)))
                .flatMap(tuple -> payGateway.savePay(tuple.getT2().toBuilder()
                        .dateStart(LocalDate.now())
                        .dateEnd(generateDateEnd())
                        .classTake(20)
                        .payType(CARD)
                        .payNumber("N/A")
                        .build()));
    }
}
