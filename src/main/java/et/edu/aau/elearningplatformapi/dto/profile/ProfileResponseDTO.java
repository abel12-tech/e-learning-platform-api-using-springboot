package et.edu.aau.elearningplatformapi.dto.profile;

public record ProfileResponseDTO(
        Long id,
        String bio,
        String phone,
        String studentName
) {
}
