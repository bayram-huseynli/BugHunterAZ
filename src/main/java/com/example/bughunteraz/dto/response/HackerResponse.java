package com.example.bughunteraz.dto.response;

<<<<<<< HEAD
import jakarta.persistence.Entity;
=======
>>>>>>> origin/bayram
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
<<<<<<< HEAD
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
=======
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
>>>>>>> origin/bayram
public class HackerResponse {

    Long id;

<<<<<<< HEAD
=======
    String username;

>>>>>>> origin/bayram
    String password;

    String email;

    String role;

<<<<<<< HEAD
    String name;

=======
>>>>>>> origin/bayram
    String portfolio;
}
