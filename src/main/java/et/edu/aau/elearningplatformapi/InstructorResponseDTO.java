package et.edu.aau.elearningplatformapi;

import java.util.List;

public record InstructorResponseDTO(
        Long id,
        String name,
        String email,
        List<String> courses
) {
}
