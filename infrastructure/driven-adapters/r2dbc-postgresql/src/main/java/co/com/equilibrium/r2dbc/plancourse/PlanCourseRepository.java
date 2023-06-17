package co.com.equilibrium.r2dbc.plancourse;

import co.com.equilibrium.model.plancourses.PlanCourse;
import co.com.equilibrium.r2dbc.plancourse.data.PlanCourseData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;


public interface PlanCourseRepository extends ReactiveCrudRepository<PlanCourseData, String> {

    @Query("DELETE FROM plan_courses WHERE id_plan = :#{#p.idPlan} AND id_course = :#{#p.idCourse} RETURNING *")
    Mono<PlanCourseData> deleteCourse(@Param("p") PlanCourse planCourse);
}
