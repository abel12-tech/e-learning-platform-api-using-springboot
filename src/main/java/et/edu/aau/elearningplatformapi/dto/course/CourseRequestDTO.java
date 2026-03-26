package et.edu.aau.elearningplatformapi.dto.course;

public record CourseRequestDTO(
        String title,
        Integer maxEnrollment,
        Long instructorId
) {}