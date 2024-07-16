package az.bughunteraz.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDto implements UserDto {

    @NotBlank
    String companyName;

    @Email
    @NotBlank
    String email;

    @NotBlank
    String contactInfo;

    String password;
}
