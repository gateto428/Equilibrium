package co.com.equilibrium.model.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Error implements Serializable {
    private String reason;
    private String domain;
    private String code;
    private String message;
}
