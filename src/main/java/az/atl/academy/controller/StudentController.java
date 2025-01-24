package az.atl.academy.controller;

import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.model.dto.ExamResultLightDto;
import az.atl.academy.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/applyCourse/{courseId}")
    public ResponseEntity<Void> applyCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        studentService.applyCourse(courseId, courseDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/applyExam/{examId}")
    public ResponseEntity<Void> applyExam(@PathVariable Long examId, @RequestParam Long courseId) {
        studentService.applyExam(examId, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getExamResult/{examId}")
    public ExamResultLightDto getExamResult(@PathVariable Long examId) {
        return studentService.getExamResult(examId);
    }
}
