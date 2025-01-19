package az.atl.academy.model.dto;

import az.atl.academy.model.enumeration.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLightDto {
    Long id;
    String username;
    String email;
    Role role;
}
