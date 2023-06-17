package co.com.equilibrium.emailAdapter.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailProperties {
    private String host;
    private String port;
    private String auth;
    private String starttls;
    private String email;
    private String password;
}
