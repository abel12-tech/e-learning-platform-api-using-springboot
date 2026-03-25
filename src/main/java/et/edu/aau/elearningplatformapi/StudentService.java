package et.edu.aau.elearningplatformapi;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }


    @Transactional(readOnly = true)
    public List<StudentDTO> findAllDTOs() {
        return studentRepository.findAllWithCoursesAndProfile().stream()
                .map(this::toDTO)
                .toList();
    }


    public StudentDTO findDTOById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return toDTO(student);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public void enroll(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        if (course.getStudents().size() >= course.getMaxEnrollment()) {
            throw new IllegalStateException("Course is full");
        }

        student.getCourses().add(course);
        studentRepository.save(student);
    }

    public void unenroll(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().remove(course);
        studentRepository.save(student);
    }

    private StudentDTO toDTO(Student student) {
        List<String> courseTitles = student.getCourses() != null
                ? List.copyOf(student.getCourses()).stream()
                .map(Course::getTitle)
                .collect(Collectors.toList())
                : List.of();

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getProfile() != null ? student.getProfile().getBio() : null,
                student.getProfile() != null ? student.getProfile().getPhone() : null,
                courseTitles
        );
    }
}