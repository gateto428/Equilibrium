package co.com.equilibrium.api.services.pay;

import co.com.equilibrium.api.commons.handlers.ValidatorHandler;
import co.com.equilibrium.api.commons.util.ParamsUtil;
import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.dto.PayDTO;
import co.com.equilibrium.api.dto.ResponseDTO;
import co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.usecase.Person.PersonUseCase;
import co.com.equilibrium.usecase.pay.PayUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PayHandler {
    private final PayUseCase useCase;
    private final PersonUseCase personUseCase;
    private final ValidatorHandler validatorHandler;

    private final ObjectMapper objectMapper;

    public Mono<ServerResponse> savePay(ServerRequest request) {
        return request.bodyToMono(PayDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(PayDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(PayDTO -> validatorHandler.validateObject(PayDTO, OnCreate.class))
                .flatMap(PayDTO -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(PayDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(PayDTO::toModel)
                .flatMap(useCase::savePay)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> payEpayco(ServerRequest request) {
        return Mono.just(request.queryParams().getFirst("x_cod_response"))
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .zipWith(ParamsUtil.getPayParams(request)
                        .flatMap(PayDTO::toModel))
                .flatMap(useCase::payEpayco)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }
}
