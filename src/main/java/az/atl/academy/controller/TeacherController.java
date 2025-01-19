package az.atl.academy.controller;

import az.atl.academy.model.dto.*;
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
    public CourseLightDto createCourse(@RequestBody CourseDto courseDto){
        return teacherService.createCourse(courseDto);
    }

    @PostMapping("/createExam")
    public ExamLightDto createExam(@RequestBody ExamDto examDto){
        return teacherService.createExam(examDto);
    }

    @PostMapping("/addExamResult")
    public ResponseEntity<Void> addExamResult(
            @RequestParam Long examId,
            @RequestParam Long studentId,
            @RequestParam Long score){
        teacherService.addExamResult(examId, studentId, score);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteCourse/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long id) {
        teacherService.deleteCourseById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteExam/{id}")
    public ResponseEntity<Void> deleteExamById(@PathVariable Long id) {
        teacherService.deleteExamById(id);
        return ResponseEntity.ok().build();
    }
}
