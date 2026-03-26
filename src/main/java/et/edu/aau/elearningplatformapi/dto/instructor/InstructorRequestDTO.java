package et.edu.aau.elearningplatformapi.dto.instructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InstructorRequestDTO(
        @NotBlank String name,
        @Email String email
) {
}
