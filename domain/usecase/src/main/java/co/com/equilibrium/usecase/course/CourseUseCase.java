package co.com.equilibrium.usecase.course;

import co.com.equilibrium.model.commons.exceptions.BussinessException;
import co.com.equilibrium.model.course.Course;
import co.com.equilibrium.model.course.gateways.CourseGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.Map;

import static co.com.equilibrium.model.commons.enums.BusinessErrorMessage.COURSE_NOT_FOUND;

@RequiredArgsConstructor
public class CourseUseCase {
    private final CourseGateway courseGateway;
    public Mono<Course> saveCourse(Course course) {
        return courseGateway.saveCourse(course)
                .thenReturn(course);
    }

    public Mono<Course> findById(Course course) {
        return courseGateway.findById(course)
                .switchIfEmpty(Mono.error(new BussinessException(COURSE_NOT_FOUND)));
    }

    public Mono<Course> updateCourse(Course course) {
        return courseGateway.findById(course)
                .switchIfEmpty(Mono.error(new BussinessException(COURSE_NOT_FOUND)))
                .flatMap(c -> courseGateway.updateCourse(course));
    }

    public Mono<Course> deleteByID(Course course) {
        return courseGateway.findById(course)
                .switchIfEmpty(Mono.error(new BussinessException(COURSE_NOT_FOUND)))
                .flatMap(courseGateway::deleteById);
    }

    public Mono<List<Course>> findAll(Tuple2<String, String> objects) {
        return courseGateway.findAll(Integer.parseInt(objects.getT1()), Integer.parseInt(objects.getT2()))
                .collectList();
    }

    public Mono<Map<String, Long>> countCourse() {
        return courseGateway.countCourse();
    }
}
