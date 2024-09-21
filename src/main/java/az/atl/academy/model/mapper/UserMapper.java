package az.atl.academy.model.mapper;

import az.atl.academy.model.dto.UserDto;
import az.atl.academy.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role.role", target = "role")
    UserDto entityToDto(UserEntity userEntity);

    @Mapping(source = "role", target = "role.role")
    UserEntity dtoToEntity(UserDto userDto);
}