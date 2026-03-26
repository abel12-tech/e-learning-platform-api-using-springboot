package et.edu.aau.elearningplatformapi.controller;

import et.edu.aau.elearningplatformapi.dto.course.CourseRequestDTO;
import et.edu.aau.elearningplatformapi.dto.course.CourseResponseDTO;
import et.edu.aau.elearningplatformapi.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService){

        this.courseService = courseService;
    }

    @PostMapping
    public CourseResponseDTO create(@RequestBody CourseRequestDTO dto) {
        return courseService.create(dto);
    }

    @GetMapping
    public List<CourseResponseDTO> getAll() {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public CourseResponseDTO getById(@PathVariable Long id){
        return courseService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        courseService.delete(id);
    }


}
