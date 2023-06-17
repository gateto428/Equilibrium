package co.com.equilibrium.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Data
@Builder(toBuilder = true)
public class ResponseDTO<T> {
    private MetaDTO.Meta meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> ResponseDTO success(T data, ServerRequest request) {
        return ResponseDTO.builder()
                .meta(MetaDTO.build(data, request))
                .data(data)
                .build();
    }

    public static <T> Mono<ServerResponse> responseOk(T response) {
        return buildResponse(OK, response);
    }

    public static <T> Mono<ServerResponse> responseFail(T body) {
        return buildResponse(INTERNAL_SERVER_ERROR, body);
    }

    private static <T> Mono<ServerResponse> buildResponse(HttpStatus status, T response) {
        return ServerResponse.status(status)
                .contentType(APPLICATION_JSON)
                .bodyValue(response);
    }
}
