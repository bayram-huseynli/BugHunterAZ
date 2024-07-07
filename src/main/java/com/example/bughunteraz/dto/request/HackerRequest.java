package com.example.bughunteraz.dto.request;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HackerRequest {

    String password;

    String email;

    String role;

    String name;

    String portfolio;
}
