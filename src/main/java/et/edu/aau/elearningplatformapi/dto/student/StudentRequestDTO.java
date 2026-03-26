package et.edu.aau.elearningplatformapi.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentRequestDTO(
        @NotBlank String name,
        @Email String email,
        String bio,
        String phone

) {
}
