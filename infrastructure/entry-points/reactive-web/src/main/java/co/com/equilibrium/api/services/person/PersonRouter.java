package co.com.equilibrium.api.services.person;

import co.com.equilibrium.api.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
public class PersonRouter extends PersonDocumentationApi {
    private static final String ALL = "/all";
    private static final String LOGIN = "/login";
    private static final String SINGIN = "/singin";
    private static final String PROFILE = "/profile";
    private static final String RESTORE = "/restore-password";
    private final ApiProperties apiProperties;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionPerson(PersonHandler personHandler) {
        final String url = apiProperties.getPerson();
        return route().POST(url, accept(APPLICATION_JSON), personHandler::savePerson, save()).build()
                .and(route().POST(url + LOGIN, accept(APPLICATION_JSON), personHandler::loginPerson, login()).build())
                .and(route().POST(url + SINGIN, accept(APPLICATION_JSON), personHandler::singInPerson, singIn()).build())
                .and(route().GET(url + PROFILE, accept(APPLICATION_JSON), personHandler::findById, findById()).build())
                .and(route().DELETE(url, accept(APPLICATION_JSON), personHandler::deactivatePerson, deactivatePerson()).build())
                .and(route().PUT(url, accept(APPLICATION_JSON), personHandler::activatePerson, activatePerson()).build())
                .and(route().PUT(url + PROFILE, accept(APPLICATION_JSON), personHandler::updatePerson, updatePerson()).build())
                .and(route().PUT(url + RESTORE, accept(APPLICATION_JSON), personHandler::updatePassword, updatePassword()).build())
                .and(route().GET(url + ALL, accept(APPLICATION_JSON), personHandler::getAll, getAll()).build());
    }
}
