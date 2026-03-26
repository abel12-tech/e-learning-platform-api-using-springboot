package et.edu.aau.elearningplatformapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService){

        this.instructorService = instructorService;
    }
    @GetMapping
    public ResponseEntity<List<InstructorResponseDTO>> getAll(){
        return ResponseEntity.ok(instructorService.findAll());
    }
}
