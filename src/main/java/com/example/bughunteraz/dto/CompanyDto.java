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
public class CompanyDto {

    @Column(unique = true, nullable = false)
    String companyName;

    @Email
    @Column(unique = true, nullable = false)
    String email;

    @NotBlank
    String contactInfo;

    @Size(min = 6, max = 12)
    @NotBlank
    String password;
}
