package az.atl.academy.repository;


import az.atl.academy.model.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findRoleById(Long id);
}
