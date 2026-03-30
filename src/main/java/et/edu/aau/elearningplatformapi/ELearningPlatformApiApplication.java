package et.edu.aau.elearningplatformapi;

import et.edu.aau.elearningplatformapi.entity.*;
import et.edu.aau.elearningplatformapi.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class ELearningPlatformApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ELearningPlatformApiApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            StudentRepository studentRepository,
            ProfileRepository profileRepository,
            InstructorRepository instructorRepository,
            CourseRepository courseRepository
    ) {
        return args -> {
            // Instructor
            Instructor instructor = Instructor.builder()
                    .name("Dr. John Smith")
                    .email("john.smith@aau.edu.et")
                    .build();
            instructorRepository.save(instructor);

            // Courses linked to instructor
            Course javaCourse = Course.builder()
                    .title("Introduction to Java")
                    .maxEnrollment(50)
                    .instructor(instructor)
                    .build();

            Course springCourse = Course.builder()
                    .title("Spring Boot Fundamentals")
                    .maxEnrollment(40)
                    .instructor(instructor)
                    .build();

            courseRepository.saveAll(List.of(javaCourse, springCourse));

            //  Profile
            Profile profile = Profile.builder()
                    .bio("Computer Science student passionate about backend development.")
                    .phone("0912345678")
                    .build();
            profileRepository.save(profile);

            // Student with profile
            Student student = Student.builder()
                    .name("Abeba Tesfaye")
                    .email("abeba@student.aau.edu.et")
                    .profile(profile)
                    .courses(new HashSet<>())
                    .build();

            // student in courses
            student.getCourses().add(javaCourse);
            student.getCourses().add(springCourse);

            javaCourse.getStudents().add(student);
            springCourse.getStudents().add(student);

            studentRepository.save(student);
            courseRepository.saveAll(List.of(javaCourse, springCourse));


            System.out.println("✅ Sample data initialized successfully!");
        };
    }
    @Bean
    CommandLineRunner initUsers(AppUserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByUsername("student").isEmpty()) {
                userRepository.save(AppUser.builder()
                        .username("student")
                        .password(encoder.encode("password"))
                        .role("STUDENT")
                        .build());
            }
            if (userRepository.findByUsername("instructor").isEmpty()) {
                userRepository.save(AppUser.builder()
                        .username("instructor")
                        .password(encoder.encode("password"))
                        .role("INSTRUCTOR")
                        .build());
            }
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(AppUser.builder()
                        .username("admin")
                        .password(encoder.encode("password"))
                        .role("ADMIN")
                        .build());
            }
        };
    }
}
