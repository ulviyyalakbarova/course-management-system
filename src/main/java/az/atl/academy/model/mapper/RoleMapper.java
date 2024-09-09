package az.atl.academy.model.mapper;

import az.atl.academy.model.dto.RoleDto;
import az.atl.academy.model.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "id", source = "roleDto.id")
    RoleEntity dtoToEntity(RoleDto roleDto);
}
