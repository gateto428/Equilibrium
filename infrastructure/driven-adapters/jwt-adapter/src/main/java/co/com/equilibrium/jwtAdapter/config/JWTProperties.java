package co.com.equilibrium.jwtAdapter.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Data
@RequiredArgsConstructor
@Configuration
public class JWTProperties {
    @Value("${adapters.jwt.code}")
    private String code;
}
