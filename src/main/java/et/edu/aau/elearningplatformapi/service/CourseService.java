package et.edu.aau.elearningplatformapi.service;

import et.edu.aau.elearningplatformapi.dto.course.CourseRequestDTO;
import et.edu.aau.elearningplatformapi.dto.course.CourseResponseDTO;
import et.edu.aau.elearningplatformapi.entity.Course;
import et.edu.aau.elearningplatformapi.entity.Instructor;
import et.edu.aau.elearningplatformapi.repository.CourseRepository;
import et.edu.aau.elearningplatformapi.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository,
                         InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    // Get all
    public List<CourseResponseDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Get By id
    public CourseResponseDTO findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return toResponseDTO(course);
    }

    // create
    public CourseResponseDTO create(CourseRequestDTO dto) {

        Instructor instructor = instructorRepository.findById(dto.instructorId())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));

        Course course = Course.builder()
                .title(dto.title())
                .maxEnrollment(dto.maxEnrollment())
                .instructor(instructor)
                .build();

        courseRepository.save(course);

        return toResponseDTO(course);
    }

    // delete
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    // mapper

    private CourseResponseDTO toResponseDTO(Course course) {
        return new CourseResponseDTO(
                course.getId(),
                course.getTitle(),
                course.getInstructor() != null
                        ? course.getInstructor().getName()
                        : null,
                course.getMaxEnrollment()
        );
    }
}