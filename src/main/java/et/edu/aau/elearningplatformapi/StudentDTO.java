package et.edu.aau.elearningplatformapi;

import java.util.List;

public record StudentDTO(
        Long id,
        String name,
        String email,
        String profileBio,
        String profilePhone,
        List<String> courses
) {}

