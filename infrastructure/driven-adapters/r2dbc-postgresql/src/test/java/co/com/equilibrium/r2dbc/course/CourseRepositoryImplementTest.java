package co.com.equilibrium.r2dbc.course;

import co.com.equilibrium.model.course.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class CourseRepositoryImplementTest {
    @Autowired
    private  CourseRepositoryImplement repositoryImplement;
    private final Course course = new Course();

    @BeforeEach
    public void init(){
       course.setDescriptionCourse("description");
       course.setNameCourse("name");
       course.setCreatorCourse("1");
       course.setIsActive(true);
    }

    @Test
    void saveCourseTest(){
        repositoryImplement.saveCourse(course)
                .subscribe(c -> StepVerifier.create(repositoryImplement.findById(course))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    @Test
    void findByIdTest(){
        course.setIdCourse(1);
        repositoryImplement.findById(course)
                .subscribe(c -> StepVerifier.create(repositoryImplement.findById(course))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    @Test
    void updateCourseTest(){
        course.setIdCourse(1);
        repositoryImplement.updateCourse(course)
                .subscribe(c -> StepVerifier.create(repositoryImplement.findById(course))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    @Test
    void deleteByIdTest(){
        course.setIdCourse(1);
        repositoryImplement.deleteById(course)
                .subscribe(c -> StepVerifier.create(repositoryImplement.findById(course))
                        .expectNextCount(1)
                        .verifyComplete());
    }

    @Test
    void findAllTest(){
        repositoryImplement.saveCourse(course);
        repositoryImplement.findAll(0, 2)
                        .subscribe(c -> StepVerifier.create(repositoryImplement.findAll(0, 2))
                                .expectNextCount(1)
                                .verifyComplete());
    }
}
