package az.atl.academy.service;

import az.atl.academy.exception.CourseNotFoundException;
import az.atl.academy.exception.ExamNotFoundException;
import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.model.dto.ExamResultLightDto;
import az.atl.academy.model.dto.UserDto;
import az.atl.academy.model.entity.CourseEntity;
import az.atl.academy.model.entity.UserEntity;
import az.atl.academy.model.mapper.UserMapper;
import az.atl.academy.repository.CourseRepository;
import az.atl.academy.repository.ExamRepository;
import az.atl.academy.repository.ExamResultRepository;
import az.atl.academy.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ExamRepository examRepository;
    private final ExamResultRepository examResultRepository;

    public List<UserDto> getAllStudents() {
        List<UserEntity> list = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (user.getRole().getId() == 3) {
                list.add(user);
            }
        });

        return list.stream().map(UserMapper.INSTANCE::toDto).toList();
    }

    public void applyCourse(Long courseId, CourseDto courseDto){
        CourseEntity course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        var studentIds = courseDto.getStudents();

        var students = userRepository.findAllById(studentIds);

        course.setStudents(students);

        courseRepository.save(course);
    }

    @Transactional
    public void applyExam(Long examId, Long courseId) {
        var exam = examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found"));

        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        var studentIds = course.getStudents()
                .stream().map(UserEntity::getId).toList();

        var students = userRepository.findAllById(studentIds);

        exam.setStudents(students);

        examRepository.save(exam);
    }

    public ExamResultLightDto getExamResult(Long examId) {
        var result = examResultRepository.findByExamId(examId)
                .orElseThrow(() -> new ExamNotFoundException("No result found for the given exam"))
                .getScore();

        return ExamResultLightDto.builder()
                .score(result)
                .build();
    }
}
