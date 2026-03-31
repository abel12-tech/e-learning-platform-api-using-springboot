package et.edu.aau.elearningplatformapi.controller;

import et.edu.aau.elearningplatformapi.dto.student.StudentRequestDTO;
import et.edu.aau.elearningplatformapi.dto.student.StudentResponseDTO;
import et.edu.aau.elearningplatformapi.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing students")
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

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> patch(
            @PathVariable Long id,
            @RequestBody StudentRequestDTO dto
    ) {
        return ResponseEntity.ok(studentService.patch(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }


}
