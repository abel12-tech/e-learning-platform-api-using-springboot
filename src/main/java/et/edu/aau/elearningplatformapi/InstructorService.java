package et.edu.aau.elearningplatformapi;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository){
        this.instructorRepository=instructorRepository;
    }
    // Create
    public InstructorResponseDTO create(InstructorRequestDTO dto){
        Instructor instructor = Instructor.builder()
                .name(dto.name())
                .email(dto.email())
                .build();
        instructorRepository.save(instructor);

        return toResponseDTO(instructor);
    }

    // Get All

    public List<InstructorResponseDTO> findAll(){
        return instructorRepository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Mapper

    private InstructorResponseDTO toResponseDTO(Instructor instructor){
        List<String> courseTitles = instructor.getCourses() != null ? instructor.getCourses().stream()
                .map(Course::getTitle)
                .toList() : List.of();

        return new InstructorResponseDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getEmail(),
                courseTitles
        );
    }
}