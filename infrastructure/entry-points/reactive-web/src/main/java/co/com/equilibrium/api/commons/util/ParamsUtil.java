package co.com.equilibrium.api.commons.util;
import co.com.equilibrium.api.dto.*;
import co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import lombok.experimental.UtilityClass;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.HEADER_MISSING_ERROR;

@UtilityClass
public class ParamsUtil {
    public  static  final String ID_PERSON = "id-person";
    public  static  final String ID_COURSE= "id-course";
    public  static  final String ID_CLASS= "id-class";
    public  static  final String ID_PLAN= "id-plan";
    public  static  final String LIMIT = "limit";
    public  static  final String OFFSET = "offset";

    private static Mono<String> ofEmptyHeaders(String value){
        return (value == null || value.isEmpty())?
                Mono.error(new TechnicalException(HEADER_MISSING_ERROR)): Mono.just(value);
    }

    private static Mono<String> ofEmptyParams(String value){
        return (value == null || value.isEmpty())?
                Mono.error(new TechnicalException(TechnicalExceptionEnum.PARAM_MISSING_ERROR)): Mono.just(value);
    }

    public static  Mono<PersonDTO> getPersonParams(ServerRequest request){
        return ofEmptyParams(request.queryParams().getFirst(ID_PERSON))
                .map(params -> PersonDTO.builder()
                        .idPerson(params)
                        .build());
    }

    public static  Mono<PayDTO> getPayParams(ServerRequest request){
        return ofEmptyParams(request.queryParams().getFirst(ID_PERSON))
                .zipWith(ofEmptyParams(request.queryParams().getFirst(ID_PLAN)))
                .map(params -> PayDTO.builder()
                        .idPlan(Integer.parseInt(params.getT2()))
                        .idPerson(params.getT1())
                        .build());
    }

    public static  Mono<CourseDTO> getCourseParams(ServerRequest request){
        return ofEmptyParams(request.queryParams().getFirst(ID_COURSE))
                .map(params -> CourseDTO.builder()
                        .idCourse(Integer.parseInt(params))
                        .build());
    }

    public static  Mono<ClassDTO> getClassParams(ServerRequest request){
        return ofEmptyParams(request.queryParams().getFirst(ID_CLASS))
                .map(params -> ClassDTO.builder()
                        .idClass(params)
                        .build());
    }

    public static Mono<Tuple2<String, String>> getLimitOffsetParams(ServerRequest request){
        return ofEmptyParams(request.queryParams().getFirst(LIMIT))
                .zipWith(ofEmptyParams(request.queryParams().getFirst(OFFSET)));
    }

    public static Mono<PlanDTO> getPlanParams(ServerRequest request) {
        return ofEmptyParams(request.queryParams().getFirst(ID_PLAN))
                .map(params -> PlanDTO.builder()
                        .idPlan(Integer.parseInt(params))
                        .build());
    }
}
