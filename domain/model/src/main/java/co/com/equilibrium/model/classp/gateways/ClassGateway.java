package co.com.equilibrium.model.classp.gateways;

import co.com.equilibrium.model.classp.Class;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.plan.Plan;
import co.com.equilibrium.model.userclass.UserClass;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClassGateway {
    Mono<Class> saveClass(Class aClass);
    Mono<Class> updateClass(Class aClass);
    Flux<Class> findAll(int limit, int offset);
    Mono<Class> deleteById(Class aClass);
    Flux<Class> findByIdUser(Person person);
    Mono<UserClass> enrollClass(UserClass userClass);
    Flux<Class> getEnrrollClass(Person person);
    Mono<UserClass> unrollClass(UserClass userClass);
    Flux<Person> getUserClass(Class clp);
}
