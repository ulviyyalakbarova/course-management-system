package az.atl.academy.repository;

import az.atl.academy.model.entity.ExamEntity;
import org.springframework.data.repository.CrudRepository;

public interface ExamRepository extends CrudRepository<ExamEntity, Long> {
}
