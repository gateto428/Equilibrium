package co.com.equilibrium.usecase.classp;

import co.com.equilibrium.model.classp.Class;
import co.com.equilibrium.model.classp.gateways.ClassGateway;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.plan.Plan;
import co.com.equilibrium.model.userclass.UserClass;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ClassUseCase {
    private final ClassGateway classGateway;

    public Mono<Class> saveClass(Class classp) {
        return classGateway.saveClass(classp.toBuilder()
                .idClass(UUID.randomUUID().toString())
                .build());
    }

    public Mono<Class> updateClass(Class aClass) {
        return classGateway.updateClass(aClass);
    }

    public Mono<List<Class>> findAll(Tuple2<String, String> objects) {
        return classGateway.findAll(Integer.parseInt(objects.getT1()), Integer.parseInt(objects.getT2()))
                .collectList();
    }

    public Mono<List<Class>> findByIdUser(Person person) {
        return classGateway.findByIdUser(person)
                .collectList();
    }

    public Mono<Class> deleteById(Class aClass) {
        return classGateway.deleteById(aClass);
    }

    public Mono<UserClass> enrollClass(UserClass userClass) {
        return classGateway.enrollClass(userClass);
    }

    public  Mono<List<Class>>getEnrrollClass(Person person) {
        return classGateway.getEnrrollClass(person)
                .collectList();
    }

    public Mono<UserClass>  unrollClass(UserClass userClass) {
        return classGateway.unrollClass(userClass);
    }

    public Mono<List<Person>> getUserClass(Class clp) {
        return classGateway.getUserClass(clp)
                .collectList();
    }
}
