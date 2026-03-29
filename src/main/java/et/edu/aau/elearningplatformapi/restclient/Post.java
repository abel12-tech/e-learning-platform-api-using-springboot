package et.edu.aau.elearningplatformapi.restclient;

public record Post(
        Integer userId,
        Integer id,
        String title,
        String body
) {
}
