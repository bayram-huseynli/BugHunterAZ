package com.example.bughunteraz.service;

import com.example.bughunteraz.dto.request.CompanyRequest;
import com.example.bughunteraz.dto.request.HackerRequest;
import com.example.bughunteraz.entity.User;
import com.example.bughunteraz.repository.UserRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    @Override
    public User registerHacker(HackerRequest hacker) {
        hacker.setPassword(passwordEncoder.encode(hacker.getPassword()));
        hacker.setRole("ROLE_HACKER");

        GoogleAuthenticatorKey key = gAuth.createCredentials();
        hacker.setTwoFactorSecret(key.getKey());

        User registeredHacker = userRepository.save(hacker);

        emailService.sendSimpleMessage(hacker.getEmail(), "Registration Confirmation",
                "You have successfully registered as a hacker. Your 2FA secret is: " + key.getKey());

        return registeredHacker;
    }

    @Override
    public User registerCompany(CompanyRequest company) {
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        company.setRole("ROLE_COMPANY");

        GoogleAuthenticatorKey key = gAuth.createCredentials();
        company.setTwoFactorSecret(key.getKey());

        User registeredCompany = userRepository.save(company);

        emailService.sendSimpleMessage(company.getEmail(), "Registration Confirmation",
                "You have successfully registered as a company. Your 2FA secret is: " + key.getKey());

        return registeredCompany;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}