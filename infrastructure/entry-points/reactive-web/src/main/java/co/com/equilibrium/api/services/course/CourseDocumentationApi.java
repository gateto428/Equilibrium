package co.com.equilibrium.api.services.course;

import co.com.equilibrium.model.error.Error;
import co.com.equilibrium.model.person.Person;
import org.springdoc.core.fn.builders.operation.Builder;

import java.util.function.Consumer;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;

public class CourseDocumentationApi {
    private static final String TAG = "Course";
    private static final String ERROR = "Error";
    private static final String SUCCESSFULL = "successful";

    protected Consumer<Builder> save() {
        return ops -> ops.tag(TAG)
                .operationId("SaveCourse").summary("Save Course")
                .description("Create new Course").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Course Save").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }
    protected Consumer<Builder> findById() {
        return ops -> ops.tag(TAG)
                .operationId("GetCourse").summary("Get Course")
                .description("Get Course").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Get Course By id"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> count() {
        return ops -> ops.tag(TAG)
                .operationId("countCourses").summary("Count Course")
                .description("Count Course").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Get Course count"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }
    protected Consumer<Builder> deleteById() {
        return ops -> ops.tag(TAG)
                .operationId("DeleteCourse").summary("Delete Course")
                .description("Delete Course").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Delete Course By id"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> updateCourse() {
        return ops -> ops.tag(TAG)
                .operationId("UpdateCourse").summary("Update Course")
                .description("Update Course").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Update Save").required(true)
                        .implementation(Person.class))
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
