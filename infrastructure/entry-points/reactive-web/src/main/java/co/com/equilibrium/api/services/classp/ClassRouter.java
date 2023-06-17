package co.com.equilibrium.api.services.classp;

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
public class ClassRouter extends ClassDocumentationApi {
    private static final String ALL = "/all";
    private static final String USER = "/user";
    private static final String INSCRIPTION = "/inscription";
    private final ApiProperties apiProperties;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionClass(ClassHandler classHandler) {
        final String url = apiProperties.getClassP();
        return route().POST(url, accept(APPLICATION_JSON), classHandler::saveClass, save()).build()
                .and(route().PUT(url , accept(APPLICATION_JSON), classHandler::updateClass, updateClass()).build())
                .and(route().GET(url + ALL , accept(APPLICATION_JSON), classHandler::findAll, getAll()).build())
                .and(route().GET(url + USER , accept(APPLICATION_JSON), classHandler::findByIdUSER, findByIdUser()).build())
                .and(route().DELETE(url , accept(APPLICATION_JSON), classHandler::deleteById, deleteById()).build())
                .and(route().POST(url + USER,accept(APPLICATION_JSON), classHandler::enrollClass, enrollClass()).build())
                .and(route().DELETE(url + USER,accept(APPLICATION_JSON), classHandler::unrollClass, enrollClass()).build())
                .and(route().GET(url + INSCRIPTION,accept(APPLICATION_JSON), classHandler::getEnrrollClass, enrollClass()).build())
                .and(route().GET(url + ALL + USER,accept(APPLICATION_JSON), classHandler::getUserClass, getUserCLass()).build());
    }
}
