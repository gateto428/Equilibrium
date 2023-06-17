package co.com.equilibrium.api.services.plan;

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
public class PlanRouter extends PlanDocumentationApi{
    private static final String ALL = "/all";
    private static final String ADD_COURSE = "/add-course";
    private static final String DELETE_COURSE = "/delete-course";
    private static final String GET_BY_USER = "/user";
    private final ApiProperties apiProperties;
    @Bean
    public RouterFunction<ServerResponse> routerFunctionPlan(PlanHandler handler){
        final String url = apiProperties.getPlan();
        return route().POST(url, accept(APPLICATION_JSON), handler::savePlan, save()).build()
                .and(route().GET(url , accept(APPLICATION_JSON), handler::findById, findById()).build())
                .and(route().GET(url + ALL , accept(APPLICATION_JSON), handler::findAll, findAll()).build())
                .and(route().DELETE(url , accept(APPLICATION_JSON), handler::deleteById, deleteById()).build())
                .and(route().PUT(url, accept(APPLICATION_JSON), handler::updatePlan, update()).build())
                .and(route().POST(url + ADD_COURSE, accept(APPLICATION_JSON), handler::addCourse, addCourse()).build())
                .and(route().DELETE(url + DELETE_COURSE, accept(APPLICATION_JSON), handler::deleteCourse, deleteCourse()).build())
                .and((route().GET(url + GET_BY_USER, accept(APPLICATION_JSON), handler::getPlanById, getPlanById())).build());
    }
}
