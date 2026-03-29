package et.edu.aau.elearningplatformapi.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Learning Platform API")
                        .version("1.0")
                        .description("API for managing students, courses, instructors, and enrollment")
                );
    }
}