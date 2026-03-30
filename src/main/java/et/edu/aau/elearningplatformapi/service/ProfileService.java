package et.edu.aau.elearningplatformapi.service;

import et.edu.aau.elearningplatformapi.dto.profile.ProfileRequestDTO;
import et.edu.aau.elearningplatformapi.dto.profile.ProfileResponseDTO;
import et.edu.aau.elearningplatformapi.entity.Profile;
import et.edu.aau.elearningplatformapi.entity.Student;
import et.edu.aau.elearningplatformapi.exception.ResourceNotFoundException;
import et.edu.aau.elearningplatformapi.repository.StudentRepository;
import et.edu.aau.elearningplatformapi.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    private final StudentRepository studentRepository;
    private final ProfileRepository profileRepository;

    public ProfileService(StudentRepository studentRepository, ProfileRepository profileRepository) {
        this.studentRepository = studentRepository;
        this.profileRepository = profileRepository;
    }

    // GET
    public ProfileResponseDTO getProfile(Long studentId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Profile profile = student.getProfile();
        if(profile == null) throw new ResourceNotFoundException("Profile not found");
        return toResponseDTO(profile);
    }

    // CREATE
    public ProfileResponseDTO createProfile(Long studentId, ProfileRequestDTO dto){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if(student.getProfile() != null){
            throw new IllegalStateException("Profile already exists for this student");
        }

        Profile profile = Profile.builder()
                .bio(dto.bio())
                .phone(dto.phone())
                .student(student)
                .build();

        student.setProfile(profile);
        profileRepository.save(profile);
        studentRepository.save(student);

        return toResponseDTO(profile);
    }

    // UPDATE
    public ProfileResponseDTO updateProfile(Long studentId, ProfileRequestDTO dto){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Profile profile = student.getProfile();
        if(profile == null) throw new ResourceNotFoundException("Profile not found");

        profile.setBio(dto.bio());
        profile.setPhone(dto.phone());

        profileRepository.save(profile);
        return toResponseDTO(profile);
    }

    // DELETE
    public void deleteProfile(Long studentId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Profile profile = student.getProfile();
        if(profile == null) throw new ResourceNotFoundException("Profile not found");

        student.setProfile(null);
        studentRepository.save(student);
        profileRepository.delete(profile);
    }

    // Mapper
    private ProfileResponseDTO toResponseDTO(Profile profile){
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getBio(),
                profile.getPhone()
        );
    }
}