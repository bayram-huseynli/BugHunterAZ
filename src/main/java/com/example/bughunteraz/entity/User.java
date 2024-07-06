package com.example.bughunteraz.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.logging.Level;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     String username;
     String password;
     String email;
     String role;

    // For Hackers
     String portfolio;

    // For Companies
     String companyName;
     String contactInfo;
}
