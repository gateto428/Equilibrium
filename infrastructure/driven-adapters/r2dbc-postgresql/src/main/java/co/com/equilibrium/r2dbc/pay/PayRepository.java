package co.com.equilibrium.r2dbc.pay;

import co.com.equilibrium.r2dbc.pay.data.PayData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface PayRepository extends ReactiveCrudRepository<PayData, String> {

}
