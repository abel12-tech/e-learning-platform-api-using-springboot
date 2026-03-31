package et.edu.aau.elearningplatformapi.controller;

import et.edu.aau.elearningplatformapi.dto.instructor.InstructorRequestDTO;
import et.edu.aau.elearningplatformapi.dto.instructor.InstructorResponseDTO;
import et.edu.aau.elearningplatformapi.dto.student.StudentRequestDTO;
import et.edu.aau.elearningplatformapi.dto.student.StudentResponseDTO;
import et.edu.aau.elearningplatformapi.service.InstructorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@Tag(name = "Instructor Management", description = "APIs for managing instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService){

        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<InstructorResponseDTO> create(@Valid @RequestBody InstructorRequestDTO dto){
        return ResponseEntity.ok(instructorService.create(dto));
    }
    @GetMapping
    public ResponseEntity<List<InstructorResponseDTO>> getAll(){
        return ResponseEntity.ok(instructorService.findAll());
    }
    @GetMapping("/{id}")
    public InstructorResponseDTO getById(@PathVariable Long id) {
        return instructorService.findById(id);
    }

    @PatchMapping("/{id}")
    public InstructorResponseDTO patch(
            @PathVariable Long id,
            @RequestBody InstructorRequestDTO dto
    ) {
        return instructorService.patch(id, dto);
    }
}
