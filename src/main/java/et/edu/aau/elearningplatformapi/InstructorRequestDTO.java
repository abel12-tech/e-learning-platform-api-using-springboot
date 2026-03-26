package et.edu.aau.elearningplatformapi;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InstructorRequestDTO(
        @NotBlank String name,
        @Email String email
) {
}
