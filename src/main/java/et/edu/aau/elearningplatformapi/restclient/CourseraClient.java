package et.edu.aau.elearningplatformapi.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "courseraClient", url = "https://api.coursera.org/api")
public interface CourseraClient {

    @GetMapping("/courses.v1")
    Map<String, Object> getCourses(
            @RequestParam(value = "start", required = false) Integer start,
            @RequestParam(value = "limit", required = false) Integer limit
    );
}