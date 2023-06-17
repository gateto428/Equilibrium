package co.com.equilibrium.api.services.pay;

import co.com.equilibrium.api.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
public class PayRouter extends PayDocumentationApi {
    private static final String CONFIRMATION = "/confirmation";
    private final ApiProperties apiProperties;
    @Bean
    public RouterFunction<ServerResponse> routerFunctionPay(PayHandler handler){
        final String url = apiProperties.getPay();
        return route().POST(url, accept(APPLICATION_JSON), handler::savePay, save()).build()
                .and(route().POST(url + CONFIRMATION, accept(APPLICATION_JSON), handler::payEpayco, save()).build());
    }
}
