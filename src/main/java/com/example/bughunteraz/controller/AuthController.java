package com.example.bughunteraz.controller;

import com.example.bughunteraz.dto.request.LoginRequest;
import com.example.bughunteraz.dto.response.AuthenticationResponse;
import com.example.bughunteraz.jwt.JwtTokenProvider;
import com.example.bughunteraz.service.CustomUserDetailsServiceImpl;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final CustomUserDetailsServiceImpl userDetailsService;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        boolean is2FAVerified = userDetailsService.verifyCode(loginRequest.getSecret(), loginRequest.getCode());
        if (!is2FAVerified) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid 2FA code");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/generate")
    public String generate2FA() {
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("BugHunterAz", "ruafyseyidov@gmail.com", key);
    }
}
