package et.edu.aau.elearningplatformapi.repository;

import et.edu.aau.elearningplatformapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {

}
