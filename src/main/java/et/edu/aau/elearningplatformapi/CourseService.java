package et.edu.aau.elearningplatformapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Return DTOs instead of raw entities
    public List<CourseDTO> findAllDTOs() {
        return courseRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    public CourseDTO findDTOById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return toDTO(course);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO toDTO(Course course) {
        return new CourseDTO(
                course.getId(),
                course.getTitle(),
                course.getInstructor() != null ? course.getInstructor().getName() : null,
                course.getMaxEnrollment()
        );
    }
}