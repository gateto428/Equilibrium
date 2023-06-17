package co.com.equilibrium.r2dbc.course.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("course")
public class CourseData implements Persistable<String> {
    @Id
    private Integer idCourse;
    private String descriptionCourse;
    private String nameCourse;
    private String creatorCourse;
    private Boolean isActive;

    @Override
    public String getId() {
        return this.idCourse.toString();
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
