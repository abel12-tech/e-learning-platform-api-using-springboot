package et.edu.aau.elearningplatformapi.controller;

import et.edu.aau.elearningplatformapi.dto.enrollment.EnrollmentRequestDTO;
import et.edu.aau.elearningplatformapi.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final StudentService studentService;

    public EnrollmentController(StudentService studentService){
        this.studentService = studentService;
    }
    // enroll
    @PostMapping
    public ResponseEntity<Void> enroll(@RequestBody EnrollmentRequestDTO dto){
        studentService.enroll(dto.studentId(), dto.courseId());
        return ResponseEntity.status(201).build();
    }
    // unenroll
    @DeleteMapping
    public ResponseEntity<Void> unenroll(@RequestBody EnrollmentRequestDTO dto){
        studentService.unenroll(dto.studentId(), dto.courseId());
        return ResponseEntity.noContent().build();
    }
}
