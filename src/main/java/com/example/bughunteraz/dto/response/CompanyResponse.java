package com.example.bughunteraz.dto.response;

import com.example.bughunteraz.entity.Role;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {

    Long id;

    String password;

    String email;

    String companyName;

    String contactInfo;

    String twoFactorSecret;

    Role role;
}
