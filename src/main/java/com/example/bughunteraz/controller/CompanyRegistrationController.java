package com.example.bughunteraz.controller;

import com.example.bughunteraz.dto.request.CompanyRequest;
import com.example.bughunteraz.dto.request.TwoFactorRequest;
import com.example.bughunteraz.entity.User;
import com.example.bughunteraz.service.UserService;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyRegistrationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerCompany(@RequestBody CompanyRequest company) {
        userService.registerCompany(company);
        return ResponseEntity.ok("Company registered successfully");
    }

    @PostMapping("/verify-2fa")
    public ResponseEntity<?> verifyCompany2FA(@RequestBody TwoFactorRequest twoFactorRequest) {
        User user = userService.findByEmail(twoFactorRequest.getEmail());
        boolean isCodeValid = new GoogleAuthenticator().authorize(user.getTwoFactorSecret(), twoFactorRequest.getCode());

        if (isCodeValid) {
            return ResponseEntity.ok("2FA verification successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid 2FA code");
        }
    }
}

