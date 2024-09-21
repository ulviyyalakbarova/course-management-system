package az.atl.academy.service;

import az.atl.academy.exception.CourseNotFoundException;
import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.model.dto.UserDto;
import az.atl.academy.model.entity.CourseEntity;
import az.atl.academy.model.entity.UserEntity;
import az.atl.academy.model.mapper.UserMapper;
import az.atl.academy.repository.CourseRepository;
import az.atl.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

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

        List<Long> studentIds = courseDto.getStudents();

        List<UserEntity> students = userRepository.findAllById(studentIds);

        course.setStudents(students);

        courseRepository.save(course);
    }
}
