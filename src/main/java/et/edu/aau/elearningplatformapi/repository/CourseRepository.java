package et.edu.aau.elearningplatformapi.repository;

import et.edu.aau.elearningplatformapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByInstructorId(Long instructorId);
}
