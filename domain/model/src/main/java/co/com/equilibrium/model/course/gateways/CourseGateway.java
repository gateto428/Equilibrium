package co.com.equilibrium.model.course.gateways;

import co.com.equilibrium.model.course.Course;
import co.com.equilibrium.model.plan.Plan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface CourseGateway {
    Mono<Void> saveCourse(Course course);

    Mono<Course> findById(Course course);

    Mono<Course> updateCourse(Course course);

    Mono<Course> deleteById(Course course);

    Flux<Course> findAll(int limit, int offset);

    Mono<Map<String, Long>> countCourse();

    Mono<Plan> findCoursesByPlan(Plan plan);
}
