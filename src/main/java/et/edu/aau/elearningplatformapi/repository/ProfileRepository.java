package et.edu.aau.elearningplatformapi.repository;

import et.edu.aau.elearningplatformapi.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
