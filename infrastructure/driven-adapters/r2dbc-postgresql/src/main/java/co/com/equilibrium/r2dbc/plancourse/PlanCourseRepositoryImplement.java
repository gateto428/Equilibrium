package co.com.equilibrium.r2dbc.plancourse;

import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.plancourses.PlanCourse;
import co.com.equilibrium.model.plancourses.gateways.PlanCourseGateway;
import co.com.equilibrium.r2dbc.AdapterOperations;
import co.com.equilibrium.r2dbc.plancourse.data.PlanCourseData;
import co.com.equilibrium.r2dbc.plancourse.data.PlanCourseMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.*;

@Repository
public class PlanCourseRepositoryImplement extends AdapterOperations<PlanCourse, PlanCourseData, String, PlanCourseRepository>
        implements PlanCourseGateway {

    public PlanCourseRepositoryImplement(PlanCourseRepository repository, PlanCourseMapper mapper) {
        super(repository, mapper::toData, mapper::toEntity);
    }


    @Override
    public Mono<PlanCourse> addCourse(PlanCourse planCourse) {
        return repository.save(this.convertToData(planCourse))
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, SAVE_PLAN_COURSE_ERROR));
    }

    @Override
    public Mono<PlanCourse> deleteCourse(PlanCourse planCourse) {
        return repository.deleteCourse(planCourse)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, DELETE_PLAN_COURSE_ERROR));
    }
}
