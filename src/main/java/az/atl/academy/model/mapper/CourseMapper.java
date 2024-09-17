package az.atl.academy.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

//    CourseEntity toEntity(CourseDto courseDto);
//
//    CourseDto toDto(CourseEntity courseEntity);
}