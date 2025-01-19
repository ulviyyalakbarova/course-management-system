package az.atl.academy.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "exams_result")
public class ExamResultEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exams_result_id_seq")
    @SequenceGenerator(name = "exams_result_id_seq", sequenceName = "exams_result_id_seq", allocationSize = 1)
    private int id;

    @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private ExamEntity exam;

    @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private UserEntity student;

    @Column(name = "score")
    private Long score;
}