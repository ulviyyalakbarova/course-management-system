package az.atl.academy.repository;

import az.atl.academy.model.entity.CourseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
}
