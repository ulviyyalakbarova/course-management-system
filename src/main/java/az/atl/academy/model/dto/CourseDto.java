package az.atl.academy.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDto {
    String title;
    String description;
    Long userId;
    SemesterDto semester;
    List<Long> students;
}
