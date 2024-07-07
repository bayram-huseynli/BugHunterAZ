package com.example.bughunteraz.dto.request;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;

    private String password;
}
