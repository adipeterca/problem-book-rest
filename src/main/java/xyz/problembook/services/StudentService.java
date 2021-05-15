package xyz.problembook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
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
     * @return one of the following ReturnCodes:
     *          - ReturnCode.studentLoginNoEntry if no entry was found in the database
     *          - ReturnCode.studentLoginIncorrectCredentials if the credentials were incorrect
     *          - ReturnCode.noErrors if no errors were found
     */
    public int loginStudent(LoginDTO loginDTO) {
        StudentEntity student = studentRepository.findFirstByName(loginDTO.getName());

        if (student == null) return ReturnCode.studentLoginNoEntry;

        if (student.getName().equals(loginDTO.getName()) && student.getHashPassword().equals(loginDTO.getHashPassword()))
            return ReturnCode.noErrors;
        return ReturnCode.studentLoginIncorrectCredentials;
    }

    public void updateAvatar(Integer studentId, Integer avatarId) {
        studentRepository.updateAvatar(studentId, avatarId);
    }

    public StudentEntity getStudentWihName(String name) {
        return studentRepository.findFirstByName(name);
    }
}
