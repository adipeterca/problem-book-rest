package xyz.problembook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.problembook.dtos.LoginDTO;
import xyz.problembook.dtos.RegisterDTO;
import xyz.problembook.entities.TeacherEntity;
import xyz.problembook.repositories.TeacherRepository;
import xyz.problembook.utilities.ReturnCode;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * Public method for registering a teacher in the database.
     * It first checks if the name exists, then it registers it
     * @param registerDTO the information provided to create a new entry in the database
     * @return a ReturnCode describing an error (if one was found) or a ReturnCode describing no errors
     */
    public int registerTeacher(RegisterDTO registerDTO) {

        if (teacherRepository.findFirstByName(registerDTO.getName()) != null) return ReturnCode.teacherNameExists;

        teacherRepository.save(new TeacherEntity(registerDTO.getName(),
                registerDTO.getSpecialField(),
                registerDTO.getHashPassword(),
                registerDTO.getAvatarId()));
        return ReturnCode.noErrors;
    }

    /**
     * Public method for logging in a user into the database.
     * @param loginDTO the entity which tries to log in
     * @return one of the following ReturnCodes:
     *          - ReturnCode.teacherLoginNoEntry if no entry was found in the database
     *          - ReturnCode.teacherLoginIncorrectCredentials if the credentials were incorrect
     *          - ReturnCode.noErrors if no errors were found
     */
    public int loginTeacher(LoginDTO loginDTO) {
        TeacherEntity teacher = teacherRepository.findFirstByName(loginDTO.getName());
        if (teacher == null) return ReturnCode.teacherLoginNoEntry;

        if (teacher.getName().equals(loginDTO.getName()) && teacher.getHashPassword().equals(loginDTO.getHashPassword()))
            return ReturnCode.noErrors;
        return ReturnCode.teacherLoginIncorrectCredentials;
    }

    /**
     * Public method which updates the avatar of a teacher.
     * @param teacherId the teacher's ID
     * @param avatarId the new avatar's ID
     */
    public void updateAvatar(Integer teacherId, Integer avatarId) {
        teacherRepository.updateAvatar(teacherId, avatarId);
    }

    public TeacherEntity getTeacherWihName(String name) {
        return teacherRepository.findFirstByName(name);
    }
}
