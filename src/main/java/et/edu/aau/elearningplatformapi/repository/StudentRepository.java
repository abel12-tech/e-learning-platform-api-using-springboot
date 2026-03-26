package et.edu.aau.elearningplatformapi.repository;

import et.edu.aau.elearningplatformapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses LEFT JOIN FETCH s.profile")
    List<Student> findAllWithCoursesAndProfile();
}
