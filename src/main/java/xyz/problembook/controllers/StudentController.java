package xyz.problembook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.problembook.dtos.LoginDTO;
import xyz.problembook.entities.StudentEntity;
import xyz.problembook.dtos.RegisterDTO;
import xyz.problembook.services.StudentService;

@RestController
@RequestMapping(path = "student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public StudentEntity getByName(@RequestParam(name = "name") String name) {
        return studentService.getStudentWihName(name);
    }

    @PostMapping("/register")
    public Integer register(@RequestBody RegisterDTO registerDTO) {
        return studentService.registerStudent(registerDTO);
    }

    @PostMapping("/login")
    public Integer login(@RequestBody LoginDTO loginDTO) {
        return studentService.loginStudent(loginDTO);
    }

    @PutMapping("/")
    public void updateAvatar(@RequestParam(name = "studentId") Integer studentId,
                             @RequestParam(name = "avatarId") Integer avatarId) {
        studentService.updateAvatar(studentId, avatarId);
    }

    // post register
    // post login
    // put update avatar
}
