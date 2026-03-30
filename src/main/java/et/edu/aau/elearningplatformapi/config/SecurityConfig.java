package et.edu.aau.elearningplatformapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->auth
                        // Student
                        .requestMatchers("/api/courses", "/api/courses/*").hasAnyRole("STUDENT", "INSTRUCTOR", "ADMIN")
                        .requestMatchers("/api/enrollments").hasAnyRole("STUDENT","ADMIN")
                        // Instructor
                        .requestMatchers("/api/courses/instructor/**").hasAnyRole("INSTRUCTOR", "ADMIN")
                        .requestMatchers("/api/courses", "/api/courses/*").hasAnyRole("INSTRUCTOR", "ADMIN")
                        // Admin
                        .requestMatchers("/api/students/**").hasRole("ADMIN")
                        .requestMatchers("/api/instructors/**").hasRole("ADMIN")
                        .requestMatchers("/api/courses/**").hasRole("ADMIN")

                        // Public
                        .requestMatchers("/api/external/**").permitAll()

                )
                .httpBasic(httpBasic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
