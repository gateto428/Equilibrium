package co.com.equilibrium.api.services.pay;

import co.com.equilibrium.model.error.Error;
import co.com.equilibrium.model.person.Person;
import org.springdoc.core.fn.builders.operation.Builder;

import java.util.function.Consumer;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;

public class PayDocumentationApi {
    private static final String TAG = "Pay";
    private static final String ERROR = "Error";
    private static final String SUCCESSFULL = "successful";

    protected Consumer<Builder> save() {
        return ops -> ops.tag(TAG)
                .operationId("SavePay").summary("Save Pay")
                .description("Create new pay").tags(new String[]{TAG})
                .requestBody(requestBodyBuilder().description("pay Save").required(true)
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
