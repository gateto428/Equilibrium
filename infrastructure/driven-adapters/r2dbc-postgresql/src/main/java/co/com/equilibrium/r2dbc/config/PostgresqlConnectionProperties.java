package co.com.equilibrium.r2dbc.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostgresqlConnectionProperties {

    private String dbname;
    private String schema;
    private String username;
    private String password;
    private String host;
    private Integer port;

}
