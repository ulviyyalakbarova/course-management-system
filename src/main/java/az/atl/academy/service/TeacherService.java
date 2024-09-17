package az.atl.academy.service;

import az.atl.academy.exception.SemesterNotFoundException;
import az.atl.academy.exception.TeacherNotFoundException;
import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.model.entity.CourseEntity;
import az.atl.academy.model.entity.SemesterEntity;
import az.atl.academy.model.entity.UserEntity;
import az.atl.academy.repository.CourseRepository;
import az.atl.academy.repository.SemesterRepository;
import az.atl.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final SemesterRepository semesterRepository;

    public Long createCourse(CourseDto courseDto){
        UserEntity teacher = userRepository.findById(courseDto.getUserId().getId())
                .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
        SemesterEntity semester = semesterRepository.findById(courseDto.getSemester().getId())
                .orElseThrow(() -> new SemesterNotFoundException("Semester not found"));

        var course = CourseEntity.builder()
                .title(courseDto.getTitle())
                .description(courseDto.getDescription())
                .teacher(teacher)
                .semester(semester)
                .build();

        courseRepository.save(course);

        return course.getId();
    }
}
