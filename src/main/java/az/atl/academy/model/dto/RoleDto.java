package az.atl.academy.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {
    @Schema(description = "Role ID, where 2 = TEACHER and 3 = STUDENT", example = "2")
    Long id;
}
