package az.atl.academy.model.mapper;

import az.atl.academy.model.dto.CourseDto;
import az.atl.academy.model.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

//    @Mapping(source = "userId", target = "teacher.id")
//    @Mapping(source = "semester.id", target = "semester.id")
//    CourseEntity toEntity(CourseDto courseDto);
//
//    @Mapping(source = "teacher.id", target = "userId")
//    @Mapping(source = "semester.id", target = "semester.id")
//    CourseDto toDto(CourseEntity courseEntity);
}