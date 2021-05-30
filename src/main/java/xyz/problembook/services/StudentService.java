package xyz.problembook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.problembook.dtos.LoggedInDTO;
import xyz.problembook.dtos.LoginDTO;
import xyz.problembook.entities.StudentEntity;
import xyz.problembook.dtos.RegisterDTO;
import xyz.problembook.repositories.StudentRepository;
import xyz.problembook.utilities.ReturnCode;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Public method for registering a student in the database.
     * It first checks if the name exists, then if the registration number exists, then it registers it
     * @param registerDTO the information provided to create a new entry in the database
     * @return a ReturnCode describing an error (if one was found) or a ReturnCode describing no errors
     */
    public int registerStudent(RegisterDTO registerDTO) {

        if (studentRepository.findFirstByName(registerDTO.getName()) != null) return ReturnCode.studentNameExists;
        if (studentRepository.findFirstByRegistrationNumber(registerDTO.getSpecialField()) != null) return ReturnCode.studentRegistrationNumberExists;

        studentRepository.save(new StudentEntity(registerDTO.getName(),
                                registerDTO.getSpecialField(),
                                registerDTO.getHashPassword(),
                                registerDTO.getAvatarId()));
        return ReturnCode.noErrors;
    }

    /**
     * Public method for logging in a user into the database.
     * @param loginDTO the entity which tries to log in
     * @return null if the student was not found or a LoggedInDTO representing the student
     */
    public LoggedInDTO loginStudent(LoginDTO loginDTO) {
        StudentEntity student = studentRepository.findFirstByName(loginDTO.getName());

        if (student == null) return null;

        if (student.getName().equals(loginDTO.getName()) && student.getHashPassword().equals(loginDTO.getHashPassword()))
            return new LoggedInDTO(student.getId(), student.getName(), student.getEmail(), student.getAvatarId());
        return null;
    }

    public void updateAvatar(Integer studentId, Integer avatarId) {
        studentRepository.updateAvatar(studentId, avatarId);
    }

    public StudentEntity getStudentWihName(String name) {
        return studentRepository.findFirstByName(name);
    }
}
