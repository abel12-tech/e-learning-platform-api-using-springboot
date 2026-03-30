package et.edu.aau.elearningplatformapi.service;

import et.edu.aau.elearningplatformapi.dto.course.CourseRequestDTO;
import et.edu.aau.elearningplatformapi.dto.course.CourseResponseDTO;
import et.edu.aau.elearningplatformapi.entity.Course;
import et.edu.aau.elearningplatformapi.entity.Instructor;
import et.edu.aau.elearningplatformapi.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return toResponseDTO(course);
    }

    // create
    public CourseResponseDTO create(CourseRequestDTO dto) {

        Instructor instructor = instructorRepository.findById(dto.instructorId())
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        Course course = Course.builder()
                .title(dto.title())
                .maxEnrollment(dto.maxEnrollment())
                .instructor(instructor)
                .build();

        courseRepository.save(course);

        return toResponseDTO(course);
    }
    // update
    public CourseResponseDTO patch(Long id, CourseRequestDTO dto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course with ID " + id + " not found")
                );

        if (dto.title() != null) {
            course.setTitle(dto.title());
        }

        if (dto.maxEnrollment() != null) {
            course.setMaxEnrollment(dto.maxEnrollment());
        }

        if (dto.instructorId() != null) {
            Instructor instructor = instructorRepository.findById(dto.instructorId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Instructor with ID " + dto.instructorId() + " not found")
                    );
            course.setInstructor(instructor);
        }

        Course updated = courseRepository.save(course);

        return toResponseDTO(updated);
    }

    // Fetch courses by instructor
    public List<CourseResponseDTO> findByInstructor(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor not found"));

        return courseRepository.findByInstructorId(instructorId).stream()
                .map(this::toResponseDTO)
                .toList();
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