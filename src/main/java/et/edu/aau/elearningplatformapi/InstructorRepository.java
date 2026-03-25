package et.edu.aau.elearningplatformapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    @Query("SELECT DISTINCT i FROM Instructor i LEFT JOIN FETCH i.courses")
    List<Instructor> findAllWithCourses();

}