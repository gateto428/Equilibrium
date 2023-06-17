package co.com.equilibrium.usecase.course;

import co.com.equilibrium.model.course.Course;
import co.com.equilibrium.model.course.gateways.CourseGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseUseCaseTest {
    @InjectMocks
    private CourseUseCase courseUseCase;
    @Mock
    private CourseGateway gateway;

    private final Course course = new Course();

    @BeforeEach
    void init() {
       course.setIdCourse(2);
       course.setNameCourse("ho");
       course.setDescriptionCourse("ho");
       course.setCreatorCourse("vre");
       course.setIsActive(true);
    }

    @Test
    void saveCourseTest() {
        when(gateway.saveCourse(any()))
                .thenReturn(Mono.empty());
        assertThat(courseUseCase.saveCourse(course)).isNotNull();
    }

    @Test
    void findByIdTest() {
        when(gateway.findById(any())).thenReturn(Mono.just(course));
        assertThat(courseUseCase.findById(course)).isNotNull();
    }

    @Test
    void updateCourseTest() {
        when(gateway.findById(any()))
                .thenReturn(Mono.just(course));

        assertThat(courseUseCase.updateCourse(course))
                .isNotNull();
    }

    @Test
    void deleteByIDTest() {
        when(gateway.findById(any()))
                .thenReturn(Mono.just(course));

        assertThat(courseUseCase.deleteByID(course))
                .isNotNull();
    }
}
