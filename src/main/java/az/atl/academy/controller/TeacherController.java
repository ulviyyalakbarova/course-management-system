package az.atl.academy.controller;

import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/createCourse")
    public Long createCourse(@RequestBody CourseDto courseDto){
        return teacherService.createCourse(courseDto);
    }
}
