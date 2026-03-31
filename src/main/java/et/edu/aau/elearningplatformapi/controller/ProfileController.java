package et.edu.aau.elearningplatformapi.controller;

import et.edu.aau.elearningplatformapi.dto.profile.ProfileRequestDTO;
import et.edu.aau.elearningplatformapi.dto.profile.ProfileResponseDTO;
import et.edu.aau.elearningplatformapi.service.ProfileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students/{studentId}/profile")
@Tag(name = "Profile Management", description = "APIs for managing profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<ProfileResponseDTO> getProfile(@PathVariable Long studentId) {
        return ResponseEntity.ok(profileService.getProfile(studentId));
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(
            @PathVariable Long studentId,
            @RequestBody ProfileRequestDTO profileRequestDTO
    ) {
        ProfileResponseDTO profileResponseDTO = profileService.createProfile(studentId, profileRequestDTO);
        return ResponseEntity.status(201).body(profileResponseDTO);
    }

    @PatchMapping
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @PathVariable Long studentId,
            @RequestBody ProfileRequestDTO profileRequestDTO
    ) {
        return ResponseEntity.ok(profileService.updateProfile(studentId, profileRequestDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProfile(@PathVariable Long studentId) {
        profileService.deleteProfile(studentId);
        return ResponseEntity.noContent().build();
    }
}