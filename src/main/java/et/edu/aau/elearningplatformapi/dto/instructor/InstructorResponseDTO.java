package et.edu.aau.elearningplatformapi.dto.instructor;

import java.util.List;

public record InstructorResponseDTO(
        Long id,
        String name,
        String email,
        List<String> courses
) {
}
