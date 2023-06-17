package co.com.equilibrium.api.services.person;

import co.com.equilibrium.api.commons.handlers.ValidatorHandler;
import co.com.equilibrium.api.commons.util.ParamsUtil;
import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnLogin;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.api.dto.PersonDTO;
import co.com.equilibrium.api.dto.ResponseDTO;
import co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.usecase.Person.PersonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PersonHandler {
    private final PersonUseCase useCase;

    private final ValidatorHandler validatorHandler;

    public Mono<ServerResponse> savePerson(ServerRequest request){
        return request.bodyToMono(PersonDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(personDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(personDTO -> validatorHandler.validateObject(personDTO, OnCreate.class))
                .flatMap(personDTO -> useCase.validateToken(request.headers()
                            .firstHeader("token"))?Mono.just(personDTO):
                            Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(PersonDTO::toModel)
                .flatMap(useCase::savePerson)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> singInPerson(ServerRequest request){
        return request.bodyToMono(PersonDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(personDTO -> validatorHandler.validateObject(personDTO, OnCreate.class))
                .flatMap(PersonDTO::toModel)
                .flatMap(useCase::savePerson)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> loginPerson(ServerRequest request){
        return  request.bodyToMono(PersonDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(personDTO -> validatorHandler.validateObject(personDTO, OnLogin.class))
                .flatMap(PersonDTO::toModel)
                .flatMap(useCase::loginPerson)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        return ParamsUtil.getPersonParams(request)
                .doOnNext(personDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(PersonDTO::toModel)
                .flatMap(personDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(personDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(useCase::findById)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> deactivatePerson(ServerRequest request){
        return ParamsUtil.getPersonParams(request)
                .doOnNext(personDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(PersonDTO::toModel)
                .flatMap(personDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(personDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(useCase::deactivatePerson)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> activatePerson(ServerRequest request){
        return ParamsUtil.getPersonParams(request)
                .doOnNext(personDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(PersonDTO::toModel)
                .flatMap(personDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(personDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(useCase::activatePerson)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> updatePerson(ServerRequest request){
        return request.bodyToMono(PersonDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(personDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(personDTO -> validatorHandler.validateObject(personDTO, OnUpdate.class))
                .flatMap(personDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(personDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(PersonDTO::toModel)
                .flatMap(useCase::updatePerson)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> updatePassword(ServerRequest request) {
        return request.bodyToMono(PersonDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(personDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(personDTO -> validatorHandler.validateObject(personDTO, OnLogin.class))
                .flatMap(personDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(personDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(PersonDTO::toModel)
                .flatMap(useCase::updatePassword)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> getAll(ServerRequest request){
        return ParamsUtil.getLimitOffsetParams(request)
                .doOnNext(params -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(params -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(params):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(useCase::findAll)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

}
