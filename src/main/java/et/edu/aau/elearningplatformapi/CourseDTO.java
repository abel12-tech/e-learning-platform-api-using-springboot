package et.edu.aau.elearningplatformapi;

public record CourseDTO(
        Long id,
        String title,
        String instructorName,
        Integer maxEnrollment
) {}

