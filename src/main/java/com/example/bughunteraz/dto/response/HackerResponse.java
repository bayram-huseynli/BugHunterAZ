package com.example.bughunteraz.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HackerResponse {

    Long id;

    String username;

    String password;

    String email;

    String role;

    String portfolio;
}
