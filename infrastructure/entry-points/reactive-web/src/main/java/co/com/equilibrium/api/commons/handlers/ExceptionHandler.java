package co.com.equilibrium.api.commons.handlers;

import co.com.equilibrium.api.dto.ResponseDTO;
import co.com.equilibrium.model.commons.exceptions.BussinessException;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.error.Error;
import lombok.extern.java.Log;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.logging.Level;

import static  co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.INTERNAL_SERVER_ERROR;

@Component
@Order(-2)
@Log
public class ExceptionHandler extends AbstractErrorWebExceptionHandler {
    private final Environment environment;

    public ExceptionHandler(ErrorAttributes errorAttributes, WebProperties properties,
                            ApplicationContext applicationContext, ServerCodecConfigurer configurer,
                            Environment environment) {
        super(errorAttributes, properties.getResources(), applicationContext);
        this.setMessageWriters(configurer.getWriters());
        this.environment = environment;

    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(org.springframework.boot.web.reactive.error.ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::rederErrorResponse);
    }

    private Mono<ServerResponse> rederErrorResponse(ServerRequest serverRequest) {
        Throwable throwable = getError(serverRequest);
        return Mono.just(serverRequest)
                .map(this::getError)
                .flatMap(Mono::error)
                .onErrorResume(TechnicalException.class, this::buildErrorResponse)
                .onErrorResume(BussinessException.class, this::buildErrorResponse)
                .onErrorResume(this::buildErrorResponse)
                .cast(Error.class)
                .map(errorResponse -> errorResponse.toBuilder()
                        .domain(serverRequest.path())
                        .build())
                .doAfterTerminate(() -> log.log(Level.WARNING, throwable.getMessage()))
                .flatMap(ResponseDTO::responseFail);
    }

    private Mono<Error> buildErrorResponse(TechnicalException ex) {
        return Mono.just(Error.builder()
                .reason(ex.getMessage())
                .code(ex.getException().getCode())
                .message(ex.getException().getMessage())
                .build());
    }

    private Mono<Error> buildErrorResponse(BussinessException ex) {
        return Mono.just(Error.builder()
                .reason(ex.getMessage())
                .code(ex.getBusinessErrorMessage().getCode())
                .message(ex.getBusinessErrorMessage().getMessage())
                .build());
    }

    private Mono<Error> buildErrorResponse(Throwable ex) {
        return Mono.just(Error.builder()
                .reason(ex.getMessage())
                .code(INTERNAL_SERVER_ERROR.getCode())
                .message(INTERNAL_SERVER_ERROR.getMessage())
                .build());
    }
}
