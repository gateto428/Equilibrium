package co.com.equilibrium.r2dbc.classp;

import co.com.equilibrium.model.classp.Class;
import co.com.equilibrium.model.classp.gateways.ClassGateway;
import co.com.equilibrium.model.commons.exceptions.TechnicalException;
import co.com.equilibrium.model.course.Course;
import co.com.equilibrium.model.course.gateways.CourseGateway;
import co.com.equilibrium.model.person.Person;
import co.com.equilibrium.model.person.gateways.PersonGateway;
import co.com.equilibrium.model.plan.Plan;
import co.com.equilibrium.model.plan.gateways.PlanGateway;
import co.com.equilibrium.model.userclass.UserClass;
import co.com.equilibrium.r2dbc.AdapterOperations;
import co.com.equilibrium.r2dbc.classp.data.ClassData;
import co.com.equilibrium.r2dbc.classp.data.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static co.com.equilibrium.model.commons.enums.TechnicalExceptionEnum.*;

@Repository
public class ClassRepositoryImplement extends AdapterOperations<Class, ClassData, String, ClassRepository>
        implements ClassGateway {

    @Autowired
    private PlanGateway planGateway;
    @Autowired
    private CourseGateway courseGateway;

    @Autowired
    private PersonGateway personGateway;

    public ClassRepositoryImplement(ClassRepository repository, ClassMapper mapper) {
        super(repository, mapper::toData, mapper::toEntity);
    }

    @Override
    public Mono<Class> saveClass(Class aClass) {
        return Mono.just(this.convertToData(aClass))
                .flatMap(repository::save)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, SAVE_CLASS_ERROR));
    }

    @Override
    public Mono<Class> updateClass(Class aClass) {
        return Mono.just(aClass)
                .flatMap(repository::updateClass)
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, UPDATE_CLASS_ERROR));
    }

    @Override
    public Flux<Class> findAll(int limit, int offset) {
        return repository.findAllCourse(limit, offset)
                .map(this::convertToEntity)
                .flatMap(aClass -> courseGateway.findById(Course
                                .builder()
                                .idCourse(aClass.getIdCourse())
                                .build())
                        .map(course -> aClass.toBuilder()
                                .course(course)
                                .build()))
                .onErrorMap(e -> new TechnicalException(e, FIND_CLASS_ERROR));
    }

    @Override
    public Mono<Class> deleteById(Class aClass) {
        return repository.findById(aClass.getIdClass())
                .flatMap(classData -> this.repository.deleteById(classData.getIdClass())
                        .thenReturn(classData))
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, DELETE_CLASS_ERROR));
    }


    @Override
    public Flux<Class> findByIdUser(Person person) {
        return planGateway.findByUserId(person)
                .flatMapIterable(Plan::getCourseList)
                .flatMap(repository::getClassByCourse)
                .map(this::convertToEntity)
                .flatMap(aClass -> courseGateway.findById(Course
                                .builder()
                                .idCourse(aClass.getIdCourse())
                                .build())
                        .map(course -> aClass.toBuilder()
                                .course(course)
                                .build()))
                .onErrorMap(e -> new TechnicalException(e, FIND_CLASS_ERROR));
    }

    @Override
    public Mono<UserClass> enrollClass(UserClass userClass) {
        return repository.getQuotasClass(userClass.getIdClass())
                .zipWith(repository.getCantErrollUsers(userClass.getIdClass()))
                .filter(tuple -> tuple.getT1().compareTo(tuple.getT2()) > 0)
                .switchIfEmpty(Mono.error(new TechnicalException(NO_VACANCIES)))
                .flatMap(tuple -> repository.getTakeClass(userClass.getStudentId()))
                .zipWith(repository.gettakeClassPlan(userClass.getStudentId()))
                .filter(tuple -> tuple.getT2().compareTo(tuple.getT1()) > 0)
                .switchIfEmpty(Mono.error(new TechnicalException(ERROR_PLAN_FINALIZATED)))
                .flatMap(tuple -> repository.enrollClass(userClass));
    }

    @Override
    public Flux<Class> getEnrrollClass(Person person) {
        return repository.getEnrrollClass(person.getIdPerson())
                .map(this::convertToEntity)
                .onErrorMap(e -> new TechnicalException(e, FIND_CLASS_ERROR));
    }

    @Override
    public Mono<UserClass> unrollClass(UserClass userClass) {
        return repository.unrollClass(userClass);
    }

    @Override
    public Flux<Person> getUserClass(Class clp) {
        return repository.getUserClass(clp.getIdClass())
                .flatMap(personGateway::findById);
    }
}
