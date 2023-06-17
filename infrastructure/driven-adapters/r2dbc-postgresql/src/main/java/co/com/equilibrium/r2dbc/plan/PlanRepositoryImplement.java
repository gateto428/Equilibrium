package co.com.equilibrium.r2dbc.plan;

import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.course.gateways.CourseGateway;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.plan.Plan;
import co.com.equilibrium.model.plan.gateways.PlanGateway;
import co.com.equilibrium.r2dbc.AdapterOperations;
import co.com.equilibrium.r2dbc.plan.data.PlanData;
import co.com.equilibrium.r2dbc.plan.data.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.*;

@Repository
public class PlanRepositoryImplement extends AdapterOperations<Plan, PlanData, String, PlanRepository>
        implements PlanGateway {

    public PlanRepositoryImplement(PlanRepository repository, PlanMapper mapper) {
        super(repository, mapper::toData, mapper::toEntity);
    }

    @Autowired
    private CourseGateway courseGateway;
    @Override
    public Mono<Plan> savePlan(Plan plan) {
        return repository.savePlan(plan)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, SAVE_PLAN_ERROR));
    }

    @Override
    public Mono<Plan> findById(Plan plan) {
        return doQuery(repository.findById(plan.getIdPlan().toString()))
                .flatMap(courseGateway::findCoursesByPlan)
                .onErrorMap(e -> new TechnicalException(e, FIND_PLAN_ERROR));
    }

    @Override
    public Flux<Plan> findAll(int limit, int offset) {
        return repository.findAll(limit, offset)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, FIND_COURSE_ERROR));
    }

    @Override
    public Mono<Long> totalElements() {
        return repository.countPlans();
    }

    @Override
    public Mono<Plan> deleteById(Plan plan) {
        return repository.deleteById(plan)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, DELETE_PLAN_ERROR));
    }

    @Override
    public Mono<Plan> updatePlan(Plan plan) {
        return repository.update(plan)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, UPDATE_PLAN_ERROR));
    }

    @Override
    public Mono<Plan> findByUserId(Person person) {
        return doQuery(repository.findByUserId(person))
                .flatMap(courseGateway::findCoursesByPlan)
                .onErrorMap(e -> new TechnicalException(e, FIND_PLAN_ERROR));
    }
}
