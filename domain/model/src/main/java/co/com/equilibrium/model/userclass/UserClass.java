package co.com.equilibrium.model.userclass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserClass {
    private String studentId;
    private String idClass;
    private boolean isPresent;
}
