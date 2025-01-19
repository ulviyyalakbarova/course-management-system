package az.atl.academy.service;

import az.atl.academy.exception.CourseNotFoundException;
import az.atl.academy.exception.ExamNotFoundException;
import az.atl.academy.exception.SemesterNotFoundException;
import az.atl.academy.exception.TeacherNotFoundException;
import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.model.dto.ExamDto;
import az.atl.academy.model.dto.UserDto;
import az.atl.academy.model.entity.CourseEntity;
import az.atl.academy.model.entity.ExamEntity;
import az.atl.academy.model.entity.SemesterEntity;
import az.atl.academy.model.entity.UserEntity;
import az.atl.academy.repository.CourseRepository;
import az.atl.academy.repository.ExamRepository;
import az.atl.academy.repository.SemesterRepository;
import az.atl.academy.repository.UserRepository;
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

    public Long createCourse(CourseDto courseDto){
        UserEntity teacher = userRepository.findById(courseDto.getUserId())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found with given ID: " + courseDto.getUserId()));
        SemesterEntity semester = semesterRepository.findById(courseDto.getSemester().getId())
                .orElseThrow(() -> new SemesterNotFoundException("Semester not found not found with given ID: " + courseDto.getSemester().getId()));

        var course = CourseEntity.builder()
                .title(courseDto.getTitle())
                .description(courseDto.getDescription())
                .teacher(teacher)
                .semester(semester)
                .build();

        courseRepository.save(course);

        return course.getId();
    }

    public void deleteCourseById(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        } else {
            throw new CourseNotFoundException("Course not found with given ID: " + id);
        }
    }

    public Long createExam(ExamDto examDto){
        CourseEntity course = courseRepository.findById(examDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found with given ID: " + examDto.getCourseId()));

        ExamEntity examEntity = ExamEntity.builder()
                .startTime(examDto.getStartTime())
                .endTime(examDto.getEndTime())
                .course(course)
                .build();

        return examRepository.save(examEntity).getId();
    }

    public void deleteExamById(Long id) {
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
        } else {
            throw new ExamNotFoundException("Exam not found with given ID: " + id);
        }
    }
}
