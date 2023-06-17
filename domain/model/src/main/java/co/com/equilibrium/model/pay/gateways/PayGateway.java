package co.com.equilibrium.model.pay.gateways;

import co.com.equilibrium.model.pay.Pay;
import reactor.core.publisher.Mono;

public interface PayGateway {
    Mono<Pay> savePay(Pay pay);
}
