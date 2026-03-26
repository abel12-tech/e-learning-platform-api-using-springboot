package et.edu.aau.elearningplatformapi.dto.course;

public record CourseResponseDTO(
        Long id,
        String title,
        String instructorName,
        Integer maxEnrollment
) {}