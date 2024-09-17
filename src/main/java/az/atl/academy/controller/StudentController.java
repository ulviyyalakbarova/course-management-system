package az.atl.academy.controller;

import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PatchMapping("/applyCourse/{courseId}")
    public ResponseEntity<Void> updateCourseStudents(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        studentService.applyCourse(courseId, courseDto);
        return ResponseEntity.ok().build();
    }
}
