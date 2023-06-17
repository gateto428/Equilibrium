package co.com.equilibrium.api.services.plan;

import co.com.equilibrium.api.commons.handlers.ValidatorHandler;
import co.com.equilibrium.api.commons.util.ParamsUtil;
import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnDelete;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.api.dto.PersonDTO;
import co.com.equilibrium.api.dto.PlanCoursesDTO;
import co.com.equilibrium.api.dto.PlanDTO;
import co.com.equilibrium.api.dto.ResponseDTO;
import co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.usecase.Person.PersonUseCase;
import co.com.equilibrium.usecase.Plan.PlanUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PlanHandler {
    private final PlanUseCase useCase;
    private final PersonUseCase personUseCase;
    private final ValidatorHandler validatorHandler;

    public Mono<ServerResponse> savePlan(ServerRequest request) {
        return request.bodyToMono(PlanDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(PlanDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(PlanDTO -> validatorHandler.validateObject(PlanDTO, OnCreate.class))
                .flatMap(PlanDTO -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(PlanDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(PlanDTO::toModel)
                .flatMap(useCase::savePlan)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return ParamsUtil.getPlanParams(request)
                .doOnNext(planDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(PlanDTO::toModel)
                .flatMap(courseDTO -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(courseDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(useCase::findById)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ParamsUtil.getLimitOffsetParams(request)
                .doOnNext(params -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(params -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(params) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(useCase::findAll)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);

    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return ParamsUtil.getPlanParams(request)
                .doOnNext(planDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(PlanDTO::toModel)
                .flatMap(courseDTO -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(courseDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(useCase::deleteById)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> updatePlan(ServerRequest request) {
        return request.bodyToMono(PlanDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(PlanDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(PlanDTO -> validatorHandler.validateObject(PlanDTO, OnUpdate.class))
                .flatMap(PlanDTO -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(PlanDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(PlanDTO::toModel)
                .flatMap(useCase::updatePlan)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> addCourse(ServerRequest request) {
        return request.bodyToMono(PlanCoursesDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(PlanCoursesDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(PlanCoursesDTO -> validatorHandler.validateObject(PlanCoursesDTO, OnCreate.class))
                .flatMap(PlanCoursesDTO -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(PlanCoursesDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(PlanCoursesDTO::toModel)
                .flatMap(useCase::addCourse)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> deleteCourse(ServerRequest request) {
        return request.bodyToMono(PlanCoursesDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(PlanCoursesDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(PlanCoursesDTO -> validatorHandler.validateObject(PlanCoursesDTO, OnDelete.class))
                .flatMap(PlanCoursesDTO -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(PlanCoursesDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(PlanCoursesDTO::toModel)
                .flatMap(useCase::deleteCourse)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> getPlanById(ServerRequest request) {
        return ParamsUtil.getPersonParams(request)
                .doOnNext(planDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(PersonDTO::toModel)
                .flatMap(PersonDTO -> personUseCase.validateToken(request.headers()
                        .firstHeader("token")) ? Mono.just(PersonDTO) :
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(useCase::findByUserId)
                .map(plan -> ResponseDTO.success(plan, request))
                .flatMap(ResponseDTO::responseOk);
    }
}
