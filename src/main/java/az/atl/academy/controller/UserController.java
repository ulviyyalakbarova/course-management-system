package az.atl.academy.controller;

import az.atl.academy.model.dto.UserDto;
import az.atl.academy.model.dto.UserLightDto;
import az.atl.academy.model.entity.UserEntity;
import az.atl.academy.service.StudentService;
import az.atl.academy.service.TeacherService;
import az.atl.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final TeacherService teacherService;
    private final StudentService studentService;
    private final UserService userService;

    @GetMapping("/allTeachers")
    public List<UserDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/allStudents")
    public List<UserDto> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/userDetails")
    public UserLightDto getUserId(@RequestParam String username) {
        return userService.getUserDetailsByUsername(username);
    }
}
