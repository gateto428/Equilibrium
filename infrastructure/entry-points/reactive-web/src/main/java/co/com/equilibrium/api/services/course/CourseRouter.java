package co.com.equilibrium.api.services.course;

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
public class CourseRouter extends  CourseDocumentationApi{
    private static final String ALL = "/all";
    private static final String COUNT = "/count";
    private final ApiProperties apiProperties;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionCourse(CourseHandler courseHandler) {
        final String url = apiProperties.getCourse();
        return route().POST(url, accept(APPLICATION_JSON), courseHandler::saveCourse, save()).build()
                .and(route().GET(url , accept(APPLICATION_JSON), courseHandler::findById, findById()).build())
                .and(route().PUT(url , accept(APPLICATION_JSON), courseHandler::updateCourse, updateCourse()).build())
                .and(route().DELETE(url , accept(APPLICATION_JSON), courseHandler::deleteById, deleteById()).build())
                .and(route().GET(url + ALL , accept(APPLICATION_JSON), courseHandler::findAll, findById()).build())
                .and(route().GET(url + COUNT , accept(APPLICATION_JSON), courseHandler::countCourses, count()).build());
    }
}
