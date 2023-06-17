package co.com.equilibrium.r2dbc.plan;

import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.plan.Plan;
import co.com.equilibrium.r2dbc.plan.data.PlanData;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface PlanRepository extends ReactiveCrudRepository<PlanData, String> {
    @Query("INSERT INTO plan (description_plan, name_plan, cost_plan, creator_plan, is_active, class_take) " +
            "VALUES (:#{#p.descriptionPlan}, :#{#p.namePlan}, :#{#p.costPlan}, :#{#p.creatorPlan}, :#{#p.isActive}, :#{#p.classTake}) RETURNING *")
    Mono<PlanData> savePlan(@Param("p") Plan plan);

    @Query("SELECT * FROM plan WHERE is_active = true ORDER BY id_plan DESC OFFSET $2 LIMIT $1;")
    Flux<PlanData> findAll(int limit, int offset);

    @Query("SELECT count(*) FROM plan WHERE is_active = true;")
    Mono<Long> countPlans();

    @Query("UPDATE plan SET is_active = false WHERE id_plan = :#{#p.idPlan} RETURNING *")
    Mono<PlanData> deleteById(@Param("p") Plan plan);
    @Query("UPDATE plan SET description_plan = :#{#p.descriptionPlan}, " +
            "name_plan = :#{#p.namePlan}, " +
            "cost_plan = :#{#p.costPlan}, " +
            "is_active = :#{#p.isActive}," +
            "class_take = :#{#p.classTake} WHERE id_plan = :#{#p.idPlan} RETURNING *")
    Mono<PlanData> update(@Param("p") Plan plan);

    @Query("SELECT * FROM schpole.plan pl " +
            "JOIN schpole.pay py ON pl.id_plan = py.id_plan " +
            "WHERE py.id_person=:#{#p.idPerson} ORDER BY py.date_start ASC LIMIT 1;")
    Mono<PlanData> findByUserId(@Param("p") Person person);
}
