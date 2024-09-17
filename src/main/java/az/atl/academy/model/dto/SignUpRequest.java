package az.atl.academy.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Schema(description = "Registration Request")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    @Schema(description = "Username", example = "John")
    @Size(min = 5, max = 30, message = "Username must contain from 5 to 30 characters")
    @NotBlank(message = "Username cannot be empty")
    String username;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Password must contain from 8 to 255 characters")
    @NotBlank(message = "Password cannot be empty")
    String password;

    @Schema(description = "Email", example = "johndoe@gmail.com")
    @Size(min = 5, max = 255, message = "Email address must contain from 5 to 255 characters")
    @NotBlank(message = "Email address cannot be empty")
    @Email(message = "Email address must be in the format of user@example.com")
    String email;

    RoleDto role;
}
