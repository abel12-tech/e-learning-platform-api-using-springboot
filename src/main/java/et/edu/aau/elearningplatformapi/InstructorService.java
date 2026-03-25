package et.edu.aau.elearningplatformapi;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor save(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Transactional(readOnly = true)
    public List<InstructorDTO> findAllDTOs() {
        return instructorRepository.findAllWithCourses().stream()
                .map(this::toDTO)
                .toList();
    }

    private InstructorDTO toDTO(Instructor instructor) {
        List<String> courseTitles = instructor.getCourses() != null
                ? instructor.getCourses().stream()
                .map(Course::getTitle)
                .collect(Collectors.toList())
                : List.of();

        return new InstructorDTO(
                instructor.getId(),
                instructor.getName(),
                instructor.getEmail(),
                courseTitles
        );
    }
}