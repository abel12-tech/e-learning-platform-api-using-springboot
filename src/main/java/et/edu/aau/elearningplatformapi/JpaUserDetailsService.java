package et.edu.aau.elearningplatformapi;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final AppUserRepository userRepository;
    public JpaUserDetailsService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        UserDetails details = User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole()) // plain "STUDENT" → ROLE_STUDENT
                .build();

        System.out.println(">>> Loaded user: " + details.getUsername());
        System.out.println(">>> Authorities: " + details.getAuthorities());

        return details;
    }


}
