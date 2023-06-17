package co.com.equilibrium.api.services.course;

import co.com.equilibrium.api.commons.handlers.ValidatorHandler;
import co.com.equilibrium.api.commons.util.ParamsUtil;
import co.com.equilibrium.api.commons.validators.groups.OnCreate;
import co.com.equilibrium.api.commons.validators.groups.OnUpdate;
import co.com.equilibrium.api.dto.CourseDTO;
import co.com.equilibrium.api.dto.ResponseDTO;
import co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.usecase.Person.PersonUseCase;
import co.com.equilibrium.usecase.course.CourseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CourseHandler {
    private final CourseUseCase courseUseCase;
    private final PersonUseCase useCase;
    private final ValidatorHandler validatorHandler;

    public Mono<ServerResponse> saveCourse(ServerRequest request){
        return request.bodyToMono(CourseDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(courseDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(courseDTO -> validatorHandler.validateObject(courseDTO, OnCreate.class))
                .flatMap(courseDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(courseDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(CourseDTO::toModel)
                .flatMap(courseUseCase::saveCourse)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        return ParamsUtil.getCourseParams(request)
                .doOnNext(courseDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(CourseDTO::toModel)
                .flatMap(courseDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(courseDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(courseUseCase::findById)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> updateCourse(ServerRequest request) {
        return request.bodyToMono(CourseDTO.class)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .doOnNext(courseDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .doOnNext(courseDTO -> validatorHandler.validateObject(courseDTO, OnUpdate.class))
                .flatMap(courseDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(courseDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(CourseDTO::toModel)
                .flatMap(courseUseCase::updateCourse)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return ParamsUtil.getCourseParams(request)
                .doOnNext(courseDTO -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(CourseDTO::toModel)
                .flatMap(courseDTO -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(courseDTO):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(courseUseCase::deleteByID)
                .map(person -> ResponseDTO.success(person, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ParamsUtil.getLimitOffsetParams(request)
                .doOnNext(params -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(params -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(params):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(courseUseCase::findAll)
                .map(course -> ResponseDTO.success(course, request))
                .flatMap(ResponseDTO::responseOk);
    }

    public Mono<ServerResponse> countCourses(ServerRequest request) {
        return Mono.just(request)
                .doOnNext(params -> validatorHandler.validateObjectHeaders(request.headers().firstHeader("token")))
                .flatMap(params -> useCase.validateToken(request.headers()
                        .firstHeader("token"))?Mono.just(params):
                        Mono.error(new TechnicalException(TechnicalExceptionEnum.BODY_MISSING_ERROR)))
                .flatMap(e -> courseUseCase.countCourse())
                .map(course -> ResponseDTO.success(course, request))
                .flatMap(ResponseDTO::responseOk);
    }
}
