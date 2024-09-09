package az.atl.academy.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Schema(description = "Authentication Request")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignInRequest {
    @Schema(description = "Username", example = "John")
    @Size(min = 5, max = 50, message = "Username must contain from 5 to 50 characters")
    @NotBlank(message = "Username cannot be empty")
    String username;

    @Schema(description = "Password", example = "my_1secret1_password")
    @Size(min = 8, max = 255, message = "Password must contain from 8 to 255 characters")
    @NotBlank(message = "Password cannot be empty")
    String password;
}
