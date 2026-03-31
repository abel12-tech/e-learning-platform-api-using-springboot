package et.edu.aau.elearningplatformapi.restclient;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/external/coursera")
@Tag(name = "External Api", description = "APIs for consuming coursera api")
public class CourseraController {

    private final CourseraService service;

    public CourseraController(CourseraService service) {
        this.service = service;
    }

    // Get all courses
    @GetMapping("/all")
    public List<Map<String, Object>> getAllCourses() {
        return service.getAllCourses();
    }

    // Get course by ID
    @GetMapping("/courses/{id}")
    public Map<String, Object> getCourseById(@PathVariable String id) {
        return service.getCourseById(id);
    }
}