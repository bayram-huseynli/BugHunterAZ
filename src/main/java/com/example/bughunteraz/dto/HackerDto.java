package com.example.bughunteraz.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
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
public class HackerDto {

    @Column(unique = true, nullable = false)
    String name;

    @Email
    @Column(nullable = false, unique = true)
    String email;

    @Size(min = 6, max = 12)
    @Column(nullable = false, unique = true)
    String password;

    @Column(nullable = false)
    String portfolio;
}
