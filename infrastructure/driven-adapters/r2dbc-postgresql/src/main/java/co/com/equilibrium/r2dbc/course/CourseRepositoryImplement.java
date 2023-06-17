package co.com.equilibrium.r2dbc.course;

import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.course.Course;
import co.com.equilibrium.model.course.gateways.CourseGateway;
import co.com.equilibrium.model.plan.Plan;
import co.com.equilibrium.r2dbc.AdapterOperations;
import co.com.equilibrium.r2dbc.course.data.CourseData;
import co.com.equilibrium.r2dbc.course.data.CourseMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.stream.Collectors;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.*;

@Repository
public class CourseRepositoryImplement extends AdapterOperations<Course, CourseData, String, CourseRepository>
        implements CourseGateway {

    public CourseRepositoryImplement(CourseRepository repository, CourseMapper mapper) {
        super(repository, mapper::toData, mapper::toEntity);
    }

    @Override
    public Mono<Void> saveCourse(Course course) {
        return  repository.saveCourse(course)
                .onErrorMap(e -> new TechnicalException( SAVE_COURSE_ERROR))
                .then();
    }

    @Override
    public Mono<Course> findById(Course course) {
        return doQuery(repository.findById(course.getIdCourse().toString()))
                .onErrorMap(e -> new TechnicalException(e, FIND_COURSE_ERROR));
    }

    @Override
    public Mono<Course> updateCourse(Course course) {
        return repository.updateCourse(course)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, UPDATE_COURSE_ERROR));
    }

    @Override
    public Mono<Course> deleteById(Course course) {
        return repository.deleteCourseById(course)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, DELETE_COURSE_ERROR));
    }

    @Override
    public Flux<Course> findAll(int limit, int offset) {
        return repository.findAllCourse(limit, offset)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, FIND_COURSE_ERROR));
    }

    @Override
    public Mono<Map<String, Long>> countCourse() {
        return repository.countCourses()
                .map(cant -> Map.of("total Courses", cant));
    }

    @Override
    public Mono<Plan> findCoursesByPlan(Plan plan) {
        return Mono.zip(
                        Mono.just(plan),
                        repository.findCoursesByPlan(plan.getIdPlan()).collectList(),
                        (t1, t2)-> t1.withCourseList(t2.stream().map(this::convertToEntity).collect(Collectors.toList())))
                .onErrorMap(e -> new TechnicalException(e, FIND_PLAN_ERROR));
    }
}
