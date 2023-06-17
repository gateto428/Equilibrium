package co.com.equilibrium.model.plan.gateways;

import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.plan.Plan;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlanGateway {
    Mono<Plan> savePlan(Plan plan);
    Mono<Plan> findById(Plan plan);
    Flux<Plan> findAll(int limit, int offset);

    Mono<Long> totalElements();

    Mono<Plan> deleteById(Plan plan);

    Mono<Plan> updatePlan(Plan plan);

    Mono<Plan> findByUserId(Person person);
}
