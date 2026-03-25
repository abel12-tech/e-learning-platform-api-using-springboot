package et.edu.aau.elearningplatformapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAll(){
       return ResponseEntity.ok(courseService.findAllDTOs());
    }
}
