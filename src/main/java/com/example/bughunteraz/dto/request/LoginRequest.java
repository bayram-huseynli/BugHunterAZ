package com.example.bughunteraz.dto.request;

import com.example.bughunteraz.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

    @Email
    @NotEmpty
    String email;

    @NotEmpty
    @Size(min = 6, max = 12)
    String password;

    @NotEmpty
    int code;

    @NotEmpty
    String secret;

    Role role;
}
