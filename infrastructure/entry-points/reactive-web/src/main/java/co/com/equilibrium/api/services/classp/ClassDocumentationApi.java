package co.com.equilibrium.api.services.classp;

import co.com.equilibrium.model.classp.Class;
import co.com.equilibrium.model.error.Error;
import co.com.equilibrium.model.person.Person;
import org.springdoc.core.fn.builders.operation.Builder;

import java.util.function.Consumer;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;

public class ClassDocumentationApi {
    private static final String TAG = "Class";
    private static final String ERROR = "Error";
    private static final String SUCCESSFULL = "successful";

    protected Consumer<Builder> save() {
        return ops -> ops.tag(TAG)
                .operationId("SaveClass").summary("Save Class")
                .description("Create new Class").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Class Save").required(true)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> updateClass() {
        return ops -> ops.tag(TAG)
                .operationId("UpdateClass").summary("Update Class")
                .description("Update Class").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Update Save").required(true)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> getAll() {
        return ops -> ops.tag(TAG)
                .operationId("GetAllClass").summary("Get All Class")
                .description("Get All Class").tags(new String[]{TAG})
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> findByIdUser() {
        return ops -> ops.tag(TAG)
                .operationId("Get Class By id user").summary("Get Class By id user")
                .description("Get Class By id user").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Get Class By id user"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> deleteById() {
        return ops -> ops.tag(TAG)
                .operationId("DeleteClas").summary("Delete CLass")
                .description("Delete Class").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Delete Class By id"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected  Consumer<Builder> enrollClass(){
        return ops -> ops.tag(TAG)
                .operationId("EnrrollClass").summary("Enroll user Class")
                .description("Enroll user Class").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Enroll user Class").required(true)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }


    protected  Consumer<Builder>  getUserCLass(){
        return ops -> ops.tag(TAG)
                .operationId("getUserPlan").summary("Get user Plan")
                .description("Get user Plan").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Get user Plan").required(true)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Class.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }
    private <T> org.springdoc.core.fn.builders.parameter.Builder createHeader(java.lang.Class<T> clazz,
                                                                              String name, String description) {
        return parameterBuilder().in(PATH).implementation(clazz).required(true).name(name).description(description);
    }
}
