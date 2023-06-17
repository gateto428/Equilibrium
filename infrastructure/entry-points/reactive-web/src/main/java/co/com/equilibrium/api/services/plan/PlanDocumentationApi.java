package co.com.equilibrium.api.services.plan;

import co.com.equilibrium.model.error.Error;
import co.com.equilibrium.model.person.Person;
import org.springdoc.core.fn.builders.operation.Builder;

import java.util.function.Consumer;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;

public class PlanDocumentationApi {
    private static final String TAG = "Plan";
    private static final String ERROR = "Error";
    private static final String SUCCESSFULL = "successful";

    protected Consumer<Builder> save() {
        return ops -> ops.tag(TAG)
                .operationId("SavePlan").summary("Save Plan")
                .description("Create new plan").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Plan Save").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }
    protected Consumer<Builder> addCourse() {
        return ops -> ops.tag(TAG)
                .operationId("addCoursePlan").summary("Save Course Plan")
                .description("Add Course in  plan").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("add course in plan").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> update() {
        return ops -> ops.tag(TAG)
                .operationId("updatePlan").summary("Update Plan")
                .description("Update plan").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Plan Update").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> findById() {
        return ops -> ops.tag(TAG)
                .operationId("GetPlan").summary("Get Plan")
                .description("Get Plan").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Get plan By id"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> getPlanById() {
        return ops -> ops.tag(TAG)
                .operationId("GetPlanByIdUSer").summary("Get Plan By ID User")
                .description("Get Plan By Id User").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Get plan By id User"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> findAll() {
        return ops -> ops.tag(TAG)
                .operationId("GetAllPlan").summary("Get All Plan")
                .description("Get All Plan").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Get  All plan"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> deleteById() {
        return ops -> ops.tag(TAG)
                .operationId("DeletePlan").summary("Delete Plan")
                .description("Delete Plan").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Delete plan By id"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> deleteCourse() {
        return ops -> ops.tag(TAG)
                .operationId("DeleteCourse").summary("Delete Course")
                .description("Delete Plan").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Delete Course By id"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    private <T> org.springdoc.core.fn.builders.parameter.Builder createHeader(Class<T> clazz,
                                                                              String name, String description) {
        return parameterBuilder().in(PATH).implementation(clazz).required(true).name(name).description(description);
    }
}
