package et.edu.aau.elearningplatformapi.dto.student;

import java.util.List;

public record StudentResponseDTO(
        Long id,
        String name,
        String email,
        String bio,
        String phone,
        List<String> courses
) {
}
