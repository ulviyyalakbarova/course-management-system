package az.atl.academy.service;

import az.atl.academy.exception.*;
import az.atl.academy.model.dto.*;
import az.atl.academy.model.entity.*;
import az.atl.academy.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;
    private final ExamRepository examRepository;
    private final ExamResultRepository examResultRepository;

    public List<UserDto> getAllTeachers() {
        List<UserEntity> list = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (user.getRole().getId() == 2) {
                list.add(user);
            }
        });

        List<UserDto> userDtoList = new ArrayList<>();
        list.forEach(data -> {
            userDtoList.add(UserDto.builder()
                    .username(data.getUsername())
                    .role(data.getRole().getRole())
                    .build());
        });

        return userDtoList;
    }

    public CourseLightDto createCourse(CourseDto courseDto) {
        UserEntity teacher = userRepository.findById(courseDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("Teacher not found with given ID: " + courseDto.getUserId()));
        SemesterEntity semester = semesterRepository.findById(courseDto.getSemester().getId())
                .orElseThrow(() -> new SemesterNotFoundException("Semester not found not found with given ID: " + courseDto.getSemester().getId()));

        var course = CourseEntity.builder()
                .title(courseDto.getTitle())
                .description(courseDto.getDescription())
                .teacher(teacher)
                .semester(semester)
                .build();

        courseRepository.save(course);

        return new CourseLightDto(course.getId());
    }

    public void deleteCourseById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new CourseNotFoundException("Course not found with given ID: " + id);
        }
    }

    public ExamLightDto createExam(ExamDto examDto) {
        CourseEntity course = courseRepository.findById(examDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found with given ID: " + examDto.getCourseId()));

        var examEntity = ExamEntity.builder()
                .startTime(examDto.getStartTime())
                .endTime(examDto.getEndTime())
                .course(course)
                .build();

        examRepository.save(examEntity);


        return new ExamLightDto(examEntity.getId());
    }

    public void deleteExamById(Long id) {
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
        } else {
            throw new ExamNotFoundException("Exam not found with given ID: " + id);
        }
    }

    public void addExamResult(Long examId, Long studentId, Long score) {
        var exam = examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found"));

        var student = userRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException("Student not found"));


        if (exam.getEndTime().isAfter(java.time.LocalDateTime.now())) {
            throw new ExamNotFinishedException("Exam is not finished yet");
        }

        var examResultEntity = ExamResultEntity.builder()
                .exam(exam)
                .student(student)
                .score(score)
                .build();

        examResultRepository.save(examResultEntity);
    }
}
