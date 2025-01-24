package az.atl.academy.repository;

import az.atl.academy.model.entity.ExamResultEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExamResultRepository extends CrudRepository<ExamResultEntity, Long> {

    Optional<ExamResultEntity> findByExamId(Long examId);

}
