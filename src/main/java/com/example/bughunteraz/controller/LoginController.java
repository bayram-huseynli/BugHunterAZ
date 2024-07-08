package com.example.bughunteraz.controller;

import com.example.bughunteraz.dto.request.LoginRequest;
import com.example.bughunteraz.dto.response.LoginResponse;
import com.example.bughunteraz.exception.CustomException;
import com.example.bughunteraz.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        try {
            String email = loginRequest.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, loginRequest.getPassword()));
            String token = jwtTokenProvider.createToken(email, loginRequest.getRole());

            return new LoginResponse(email, token);
        }catch (AuthenticationException e){
            throw new CustomException("Invalid email/password supplied");
        }
    }
}

