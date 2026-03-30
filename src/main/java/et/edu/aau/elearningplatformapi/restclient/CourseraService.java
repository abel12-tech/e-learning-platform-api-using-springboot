package et.edu.aau.elearningplatformapi.restclient;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseraService {

    private final CourseraClient client;

    public CourseraService(CourseraClient client) {
        this.client = client;
    }

    // Get all courses (limit 20)
    public List<Map<String, Object>> getAllCourses() {
        Map<String, Object> response = client.getCourses(0, 100);
        return (List<Map<String, Object>>) response.get("elements");
    }

    // Get course by ID
    public Map<String, Object> getCourseById(String id) {
        List<Map<String, Object>> allCourses = getAllCourses();
        return allCourses.stream()
                .filter(c -> id.equals(c.get("id").toString()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}