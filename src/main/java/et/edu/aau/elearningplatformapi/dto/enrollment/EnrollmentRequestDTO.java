package et.edu.aau.elearningplatformapi.dto.enrollment;

public record EnrollmentRequestDTO(
        Long studentId,
        Long courseId
) {}