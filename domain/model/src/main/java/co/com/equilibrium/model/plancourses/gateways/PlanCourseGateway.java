package co.com.equilibrium.model.plancourses.gateways;

import co.com.equilibrium.model.plancourses.PlanCourse;
import reactor.core.publisher.Mono;

public interface PlanCourseGateway {
    Mono<PlanCourse> addCourse(PlanCourse planCourse);

    Mono<PlanCourse> deleteCourse(PlanCourse planCourse);
}
