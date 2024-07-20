package az.bughunteraz.dto.request.hacker;

import az.bughunteraz.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HackerRequest implements UserDto {

    @Column(unique = true, nullable = false)
    @NotBlank
    String name;

    @Email
    @NotBlank
    String email;

    String password;

    String confirmPassword;

    @NotBlank
    String portfolio;
}
