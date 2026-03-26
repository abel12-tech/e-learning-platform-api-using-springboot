package et.edu.aau.elearningplatformapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, message = "Course title must be at least 5 characters long")
    @Column(nullable = false)
    private String title;

    private int maxEnrollment;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Builder.Default
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

}