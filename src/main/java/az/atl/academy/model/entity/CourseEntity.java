package az.atl.academy.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courses_id_seq")
    @SequenceGenerator(name = "courses_id_seq", sequenceName = "courses_id_seq", allocationSize = 1)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    UserEntity teacher;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    SemesterEntity semester;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "courses_students", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    List<UserEntity> students;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseEntity that = (CourseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
