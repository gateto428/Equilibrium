package co.com.equilibrium.api.services.person;

import co.com.equilibrium.model.error.Error;
import co.com.equilibrium.model.person.Person;
import org.springdoc.core.fn.builders.operation.Builder;
import java.util.function.Consumer;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;

public class PersonDocumentationApi {
    private static final String TAG = "Person";
    private static final String ERROR = "Error";
    private static final String SUCCESSFULL = "successful";

    protected Consumer<Builder> save() {
        return ops -> ops.tag(TAG)
                .operationId("SavePerson").summary("Save Person")
                .description("Create new person").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Person Save").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> singIn() {
        return ops -> ops.tag(TAG)
                .operationId("RegisterPerson").summary("Register Person")
                .description("Create new person").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Register Person").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> login() {
        return ops -> ops.tag(TAG)
                .operationId("LoginPerson").summary("Login Person")
                .description("Login person").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Login Save").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> findById() {
        return ops -> ops.tag(TAG)
                .operationId("GetPerson").summary("Get Person")
                .description("Get person").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Get Person By id"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> getAll() {
        return ops -> ops.tag(TAG)
                .operationId("GetAllPerson").summary("Get All Person")
                .description("Get All person").tags(new String[]{TAG})
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> updatePerson() {
        return ops -> ops.tag(TAG)
                .operationId("updatePerson").summary("Update Person")
                .description("Update person").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Update Person").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }

    protected Consumer<Builder> updatePassword() {
        return ops -> ops.tag(TAG)
                .operationId("updatePersonPas").summary("Update Person Password")
                .description("Update person").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("Update Person Password").required(true)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }
    protected Consumer<Builder> deactivatePerson() {
        return ops -> ops.tag(TAG)
                .operationId("inactivePerson").summary("Inactive Person")
                .description("Inactive Person").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Inactive Person By id"))
                .response(responseBuilder().responseCode("200").description(SUCCESSFULL)
                        .implementation(Person.class))
                .response(responseBuilder().responseCode("500").description(ERROR)
                        .implementation(Error.class));
    }


    protected Consumer<Builder> activatePerson() {
        return ops -> ops.tag(TAG)
                .operationId("activatePerson").summary("Activate Person")
                .description("Activate Person").tags(new String[]{TAG})
                .parameter(createHeader(String.class, "id", "Activate Person By id"))
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
