package co.com.equilibrium.r2dbc.course;

import co.com.equilibrium.model.course.Course;
import co.com.equilibrium.r2dbc.course.data.CourseData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CourseRepository extends ReactiveCrudRepository<CourseData, String> {

    @Query("UPDATE course SET description_course=:#{#c.descriptionCourse}, name_course=:#{#c.nameCourse} " +
            "WHERE id_course=:#{#c.idCourse} RETURNING *")
    Mono<CourseData> updateCourse(@Param("c") Course course);

    @Query("UPDATE course SET is_active = false WHERE id_course=:#{#c.idCourse} RETURNING *")
    Mono<CourseData> deleteCourseById(@Param("c") Course course);

    @Query("INSERT INTO course (description_course, name_course, creator_course, is_active) " +
            "VALUES (:#{#c.descriptionCourse}, :#{#c.nameCourse}, :#{#c.creatorCourse}, :#{#c.isActive})")
    Mono<Void> saveCourse(@Param("c") Course course);

    @Query("SELECT * FROM course WHERE is_active = true ORDER BY id_course DESC OFFSET $2 LIMIT $1;")
    Flux<CourseData> findAllCourse(int limit, int offset);

    @Query("SELECT count(*) FROM course WHERE is_active = true;")
    Mono<Long> countCourses();

    @Query("SELECT co.id_course, co.name_course, " +
            "co.description_course, " +
            "co.creator_course, co.is_active, " +
            "pc.number_class " +
            "FROM course co " +
            "JOIN plan_courses pc ON co.id_course = pc.id_course " +
            "WHERE co.is_active = true AND pc.id_plan = $1")
    Flux<CourseData> findCoursesByPlan(Integer idPlan);
}
