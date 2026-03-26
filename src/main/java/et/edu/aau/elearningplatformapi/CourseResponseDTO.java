package et.edu.aau.elearningplatformapi;

public record CourseResponseDTO(
        Long id,
        String title,
        String instructorName,
        Integer maxEnrollment
) {}