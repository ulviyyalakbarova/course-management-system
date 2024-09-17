package az.atl.academy.model.entity;

import az.atl.academy.model.enumeration.Semester;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@Entity
@Table(name = "semesters")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SemesterEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semesters_id_seq")
    @SequenceGenerator(name = "semesters_id_seq", sequenceName = "semesters_id_seq", allocationSize = 1)
    Long id;

    @Column(name = "semester")
    @Enumerated(EnumType.STRING)
    Semester semester;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemesterEntity that = (SemesterEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
