package co.com.equilibrium.r2dbc.classp.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("class")
public class ClassData implements Persistable<String> {
    @Id
    private String idClass;
    private LocalDate dateClass;
    private String hourClass;
    private Integer durationClass;
    private Integer quotasClass;
    private String roomClass;
    private Integer idCourse;
    private String creatorClass;
    private String coachClass;

    @Override
    public String getId() {
        return this.idClass;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
