package com.example.bughunteraz.controller;

import com.example.bughunteraz.dto.CompanyDto;
import com.example.bughunteraz.dto.HackerDto;
import com.example.bughunteraz.dto.response.UserResponse;
import com.example.bughunteraz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/hacker")
    public ResponseEntity<UserResponse> registerHacker(@Validated @RequestBody HackerDto hackerDto){
        return ResponseEntity.ok(userService.registerHacker(hackerDto));
    }

    @PostMapping("/company")
    public ResponseEntity<UserResponse> registerCompany(@Validated @RequestBody CompanyDto companyDto){
        return ResponseEntity.ok(userService.registerCompany(companyDto));
    }
}