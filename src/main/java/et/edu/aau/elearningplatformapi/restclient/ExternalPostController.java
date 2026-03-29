package et.edu.aau.elearningplatformapi.restclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExternalPostController {
    private final JsonPlaceholderFeignClient feignClient;

    public ExternalPostController(JsonPlaceholderFeignClient feignClient){
        this.feignClient = feignClient;
    }
    @GetMapping("/posts/{id}")
    public Post getWithFeign(@PathVariable Long id){
        return feignClient.getPost(id);
    }
}
