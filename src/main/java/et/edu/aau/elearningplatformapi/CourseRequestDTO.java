package et.edu.aau.elearningplatformapi;

public record CourseRequestDTO(
        String title,
        Integer maxEnrollment,
        Long instructorId
) {}