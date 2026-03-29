package et.edu.aau.elearningplatformapi.controller;

import et.edu.aau.elearningplatformapi.dto.profile.ProfileRequestDTO;
import et.edu.aau.elearningplatformapi.dto.profile.ProfileResponseDTO;
import et.edu.aau.elearningplatformapi.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students/{studentId}/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Long studentId) {
        return ResponseEntity.ok(profileService.getProfile(studentId));
    }

}