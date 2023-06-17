package co.com.equilibrium.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "adapters.entries.reactive-web")
public class ApiProperties {
    private String person;
    private String classP;
    private String course;
    private String pay;
    private String plan;
    private String planCourses;
    private String userClass;
    private String pathBase;

}
