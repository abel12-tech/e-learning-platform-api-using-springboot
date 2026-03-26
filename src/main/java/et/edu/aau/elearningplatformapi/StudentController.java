package et.edu.aau.elearningplatformapi;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<StudentResponseDTO> create(@Valid @RequestBody StudentRequestDTO dto){
        return ResponseEntity.ok(studentService.create(dto));
    }

    // Get by id

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.findById(id));
    }


    // enroll
    @PostMapping("/{studentId}/enroll/{courseId}")
    public ResponseEntity<Void> enroll(
         @PathVariable Long studentId,
         @PathVariable Long courseId
    )
    {
      studentService.enroll(studentId,courseId);

      return ResponseEntity.ok().build();
    }
    // unenroll
    @PostMapping("/{studentId}/unenroll/{courseId}")
    public ResponseEntity<Void> unenroll(@PathVariable Long studentId ,@PathVariable Long courseId){
        studentService.unenroll(studentId,courseId);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

}
