package et.edu.aau.elearningplatformapi.service;

import et.edu.aau.elearningplatformapi.dto.student.StudentRequestDTO;
import et.edu.aau.elearningplatformapi.dto.student.StudentResponseDTO;
import et.edu.aau.elearningplatformapi.entity.Course;
import et.edu.aau.elearningplatformapi.entity.Profile;
import et.edu.aau.elearningplatformapi.entity.Student;
import et.edu.aau.elearningplatformapi.repository.CourseRepository;
import et.edu.aau.elearningplatformapi.repository.ProfileRepository;
import et.edu.aau.elearningplatformapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ProfileRepository profileRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, ProfileRepository profileRepository ) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.profileRepository = profileRepository;
    }

    // Create
    public StudentResponseDTO create (StudentRequestDTO dto){
        Profile profile = Profile.builder()
                .bio(dto.bio())
                .phone(dto.phone())
                .build();
        profileRepository.save(profile);

        Student student = Student.builder()
                .name(dto.name())
                .email(dto.email())
                .profile(profile)
                .build();
        studentRepository.save(student);

        return toResponseDTO(student);
    }

    // Get All

    public List<StudentResponseDTO> findAll(){
        return studentRepository.findAllWithCoursesAndProfile()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Get By id

    public StudentResponseDTO findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return toResponseDTO(student);
    }

    // Enroll
    public void enroll(Long studentId,Long courseId){
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() ->new RuntimeException("Course not found"));

        // preventing duplicate enrollment
        if(student.getCourses().contains(course)){
            throw new IllegalStateException("Student already enrolled");
        }
        // checking max capacity
        if(course.getStudents().size() >= course.getMaxEnrollment()){
            throw new IllegalStateException("Course is full");
        }
        // Sync
        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);

    }

    // Unenroll

    public void unenroll(Long studentId, Long courseId){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        // check if enrolled
        if(!student.getCourses().contains(course)){
            throw new IllegalStateException("Student is not enrolled in this course");
        }

        student.getCourses().remove(course);
        course.getStudents().remove(student);

        studentRepository.save(student);

    }

    // Mapper

    private StudentResponseDTO toResponseDTO(Student student){
        List<String> courseTitles = student.getCourses() != null ?
                student.getCourses().stream()
                        .map(Course::getTitle)
                        .toList()
                :List.of();
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getProfile() != null ? student.getProfile().getBio() : null,
                student.getProfile() != null ? student.getProfile().getPhone() : null,
                courseTitles
        );
    }


}