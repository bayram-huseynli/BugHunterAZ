package com.example.bughunteraz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyRequest {

    String username;

    String password;

    String email;

    String role;

    String companyName;

    String contactInfo;
}
