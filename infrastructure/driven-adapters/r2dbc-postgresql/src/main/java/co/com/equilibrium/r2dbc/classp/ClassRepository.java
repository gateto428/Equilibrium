package co.com.equilibrium.r2dbc.classp;

import co.com.equilibrium.model.classp.Class;
import co.com.equilibrium.model.course.Course;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.userclass.UserClass;
import co.com.equilibrium.r2dbc.classp.data.ClassData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;


public interface ClassRepository extends ReactiveCrudRepository<ClassData, String> {

    @Query("UPDATE class SET date_class=:#{#c.dateClass}, hour_class=:#{#c.hourClass}, " +
            "duration_class=:#{#c.durationClass}, quotas_class=:#{#c.quotasClass}, " +
            "room_class=:#{#c.roomClass}, id_course=:#{#c.idCourse}, " +
            "coach_class=:#{#c.coachClass} " +
            "WHERE id_class=:#{#c.idClass} RETURNING *")
    Mono<ClassData> updateClass(@Param("c") Class aClass);

    @Query("SELECT * FROM class ORDER BY id_class DESC OFFSET $2 LIMIT $1;")
    Flux<ClassData> findAllCourse(int limit, int offset);

    @Query("SELECT * FROM class WHERE id_course=:#{#c.idCourse} AND date_class >= CURRENT_DATE ORDER BY id_class;")
    Flux<ClassData> getClassByCourse(@Param("c") Course course);

    @Query("SELECT quotas_class " +
            "FROM class " +
            "WHERE id_class = $1")
    Mono<Integer> getQuotasClass(String idClass);

    @Query("SELECT COUNT(*) " +
            "FROM User_class " +
            "WHERE id_class = $1")
    Mono<Integer> getCantErrollUsers(String idClass);

    @Query("INSERT INTO User_class (student_id, id_class, isPresent) " +
            "VALUES (:#{#uc.studentId}, :#{#uc.idClass}, :#{#uc.isPresent}) RETURNING *")
    Mono<UserClass> enrollClass(@Param("uc") UserClass userClass);

    @Query("SELECT c.* " +
            "FROM Class c " +
            "INNER JOIN User_class uc ON c.id_class = uc.id_class " +
            "WHERE uc.student_id = :studentId AND c.date_class >= CURRENT_DATE")
    Flux<ClassData> getEnrrollClass(@Param("studentId") String studentId);

    @Query("DELETE FROM User_class uc " +
            "WHERE uc.student_id = :#{#uc.studentId} AND uc.id_class = :#{#uc.idClass} RETURNING *")
    Mono<UserClass> unrollClass(@Param("uc") UserClass userClass);

    @Query("SELECT p.* " +
            "FROM user_class uc " +
            "JOIN person p ON uc.student_id = p.id_person " +
            "WHERE uc.id_class = $1;")
    Flux<Person> getUserClass(String idClass);

    @Query("SELECT COUNT(uc.id_class) " +
            "FROM Person p " +
            "JOIN Pay py ON p.id_person = py.id_person " +
            "JOIN Plan pl ON py.id_plan = pl.id_plan " +
            "JOIN Plan_Courses pc ON pl.id_plan = pc.id_plan " +
            "JOIN Class cl ON pc.id_course = cl.id_course " +
            "JOIN User_class uc ON cl.id_class = uc.id_class AND p.id_person = uc.student_id " +
            "WHERE p.id_person = $1 AND py.date_start <= cl.date_class; ")
    Mono<Integer> getTakeClass(String studentId);

    @Query("SELECT pl.class_take " +
            "FROM Person p " +
            "JOIN Pay py ON p.id_person = py.id_person " +
            "JOIN Plan pl ON py.id_plan = pl.id_plan " +
            "WHERE p.id_person = $1")
    Mono<Integer> gettakeClassPlan(String studentId);
}
