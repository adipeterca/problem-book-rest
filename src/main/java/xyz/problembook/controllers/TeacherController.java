package xyz.problembook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.problembook.dtos.LoginDTO;
import xyz.problembook.dtos.RegisterDTO;
import xyz.problembook.entities.TeacherEntity;
import xyz.problembook.services.TeacherService;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public TeacherEntity getByName(@RequestParam(name = "name") String name) {
        return teacherService.getTeacherWihName(name);
    }

    @PostMapping("/register")
    public Integer register(@RequestBody RegisterDTO registerDTO) {
        return teacherService.registerTeacher(registerDTO);
    }

    @PostMapping("/login")
    public Integer login(@RequestBody LoginDTO loginDTO) {
        return teacherService.loginTeacher(loginDTO);
    }

    @PutMapping("/")
    public void updateAvatar(@RequestParam(name = "studentId") Integer studentId,
                             @RequestParam(name = "avatarId") Integer avatarId) {
        teacherService.updateAvatar(studentId, avatarId);
    }
}
