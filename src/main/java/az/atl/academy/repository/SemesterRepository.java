package az.atl.academy.repository;

import az.atl.academy.model.entity.SemesterEntity;
import org.springframework.data.repository.CrudRepository;

public interface SemesterRepository extends CrudRepository<SemesterEntity, Long> {
}
