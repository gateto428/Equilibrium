package co.com.equilibrium.api.services.classp;

import co.com.equilibrium.api.commons.handlers.ValidatorHandler;
import co.com.equilibrium.api.commons.util.ParamsUtil;
import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.api.dto.*;
import co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.usecase.Person.PersonUseCase;
import co.com.equilibrium.usecase.classp.ClassUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClassHandler {
    private final ClassUseCase classUseCase;
    private final PersonUseCase useCase;
    private final ValidatorHandler validatorHandler;

    public Mono<ServerResponse> saveClass(ServerRequest request) {
        return request.bodyToMono(ClassDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(ClassDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(ClassDTO -> validatorHandler.validateObject(ClassDTO, OnCreate.class))
                .flatMap(ClassDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(ClassDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(ClassDTO::toModel)
                .flatMap(classUseCase::saveClass)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> updateClass(ServerRequest request) {
        return request.bodyToMono(ClassDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(ClassDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(ClassDTO -> validatorHandler.validateObject(ClassDTO, OnUpdate.class))
                .flatMap(ClassDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(ClassDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(ClassDTO::toModel)
                .flatMap(classUseCase::updateClass)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ParamsUtil.getLimitOffsetParams(request)
                .doOnNext(params -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(params -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(params):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(classUseCase::findAll)
                .map(course -> ResponseDTO.success(course, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> findByIdUSER(ServerRequest request) {
        return ParamsUtil.getPersonParams(request)
                .doOnNext(params -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(PersonDTO::toModel)
                .flatMap(params -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(params):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(classUseCase::findByIdUser)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){
        return ParamsUtil.getClassParams(request)
                .doOnNext(ClassDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(ClassDTO::toModel)
                .flatMap(ClassDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(ClassDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(classUseCase::deleteById)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> enrollClass(ServerRequest request) {
        return request.bodyToMono(UserClassDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(UserClassDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(UserClassDTO -> validatorHandler.validateObject(UserClassDTO, OnCreate.class))
                .flatMap(UserClassDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(UserClassDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(UserClassDTO::toModel)
                .flatMap(classUseCase::enrollClass)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> getEnrrollClass(ServerRequest request) {
        return ParamsUtil.getPersonParams(request)
                .doOnNext(params -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(PersonDTO::toModel)
                .flatMap(params -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(params):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(classUseCase::getEnrrollClass)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> unrollClass(ServerRequest request) {
        return request.bodyToMono(UserClassDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(UserClassDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(UserClassDTO -> validatorHandler.validateObject(UserClassDTO, OnCreate.class))
                .flatMap(UserClassDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(UserClassDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(UserClassDTO::toModel)
                .flatMap(classUseCase::unrollClass)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> getUserClass(ServerRequest request) {
        return ParamsUtil.getClassParams(request)
                .doOnNext(params -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(ClassDTO::toModel)
                .flatMap(params -> useCase.validateToken(request.headers()
                .firstHeader("token"))?Mono.just(params):
                Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(classUseCase::getUserClass)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }
}
