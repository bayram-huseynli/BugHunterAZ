package com.example.bughunteraz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HackerRequest {

    String username;

    String password;

    String email;

    String role;

    String portfolio;
}
