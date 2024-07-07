package com.example.bughunteraz.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRequest {

    String password;

    String email;

    String role;

    String companyName;

    String contactInfo;
}
