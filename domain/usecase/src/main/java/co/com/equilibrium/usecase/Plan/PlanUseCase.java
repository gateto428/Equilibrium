package co.com.equilibrium.usecase.Plan;

import co.com.equilibrium.model.commons.exceptions.BussinessException;
import co.com.equilibrium.model.course.Course;
import co.com.equilibrium.model.course.gateways.CourseGateway;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.plan.Plan;
import co.com.equilibrium.model.plan.gateways.PlanGateway;
import co.com.equilibrium.model.plancourses.PlanCourse;
import co.com.equilibrium.model.plancourses.gateways.PlanCourseGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Map;

import static co.com.equilibrium.model.commons.enums.BusinessErrorMessage.COURSE_NOT_FOUND;
import static co.com.equilibrium.model.commons.enums.BusinessErrorMessage.PLAN_NOT_FOUND;

@RequiredArgsConstructor
public class PlanUseCase {
    private final PlanGateway planGateway;
    private final CourseGateway courseGateway;
    private final PlanCourseGateway planCourseGateway;

    public Mono<Plan> savePlan(Plan plan) {
        return planGateway.savePlan(plan);
    }

    public Mono<Plan> findById(Plan plan) {
        return planGateway.findById(plan)
                .switchIfEmpty(Mono.error(new BussinessException(PLAN_NOT_FOUND)));
    }

    public Mono<Map<String, Object>> findAll(Tuple2<String, String> objects) {
        return planGateway.findAll(Integer.parseInt(objects.getT1()), Integer.parseInt(objects.getT2()))
                .collectList()
                .flatMap(list -> planGateway.totalElements()
                        .map(total -> Map.of(
                                "TotalResponse", total,
                                "actualPage", Integer.parseInt(objects.getT2()),
                                "totalPage", Math.ceil(total / Integer.parseInt(objects.getT1())),
                                "response", list)));


    }

    public Mono<Plan> deleteById(Plan plan) {
        return planGateway.deleteById(plan);
    }

    public Mono<Plan> updatePlan(Plan plan) {
        return planGateway.updatePlan(plan);
    }

    public Mono<PlanCourse> addCourse(PlanCourse planCourse) {
        return planGateway.findById(Plan.builder()
                        .idPlan(planCourse.getIdPlan())
                        .build())
                .switchIfEmpty(Mono.error(new BussinessException(PLAN_NOT_FOUND)))
                .flatMap(plan -> courseGateway.findById(Course.builder()
                        .idCourse(planCourse.getIdCourse())
                        .build()))
                .switchIfEmpty(Mono.error(new BussinessException(COURSE_NOT_FOUND)))
                .flatMap(course -> planCourseGateway.addCourse(planCourse));
    }

    public Mono<PlanCourse> deleteCourse(PlanCourse planCourse) {
        return planGateway.findById(Plan.builder()
                        .idPlan(planCourse.getIdPlan())
                        .build())
                .switchIfEmpty(Mono.error(new BussinessException(PLAN_NOT_FOUND)))
                .flatMap(plan -> courseGateway.findById(Course.builder()
                        .idCourse(planCourse.getIdCourse())
                        .build()))
                .switchIfEmpty(Mono.error(new BussinessException(COURSE_NOT_FOUND)))
                .flatMap(course -> planCourseGateway.deleteCourse(planCourse));
    }

    public Mono<Plan> findByUserId(Person person) {
        return planGateway.findByUserId(person)
                .switchIfEmpty(Mono.error(new BussinessException(PLAN_NOT_FOUND)));
    }
}
