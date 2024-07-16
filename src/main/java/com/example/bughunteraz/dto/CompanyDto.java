package com.example.bughunteraz.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
