package az.atl.academy.controller;

import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.model.dto.ExamDto;
import az.atl.academy.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/createCourse")
    public Long createCourse(@RequestBody CourseDto courseDto){
        return teacherService.createCourse(courseDto);
    }

    @PostMapping("/createExam")
    public Long createExam(@RequestBody ExamDto examDto){
        return teacherService.createExam(examDto);
    }

    @DeleteMapping("deleteCourse/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long id) {
        teacherService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("deleteExam/{id}")
    public ResponseEntity<Void> deleteExamById(@PathVariable Long id) {
        teacherService.deleteExamById(id);
        return ResponseEntity.noContent().build();
    }
}
