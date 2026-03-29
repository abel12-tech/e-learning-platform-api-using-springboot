package et.edu.aau.elearningplatformapi.service;

import et.edu.aau.elearningplatformapi.dto.profile.ProfileResponseDTO;
import et.edu.aau.elearningplatformapi.entity.Profile;
import et.edu.aau.elearningplatformapi.entity.Student;
import et.edu.aau.elearningplatformapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private final StudentRepository studentRepository;

    public ProfileService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    // get profile by student
    public ProfileResponseDTO getProfile(Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(() ->new RuntimeException("Student not found"));
        Profile profile = student.getProfile();

        if(profile == null){
            throw new RuntimeException("Profile not found");
        }
        return toDTO(profile);
    }
  // mapper

    private ProfileResponseDTO toDTO(Profile profile) {
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getBio(),
                profile.getPhone(),
                profile.getStudent() != null ? profile.getStudent().getName() : null
        );
    }

}
