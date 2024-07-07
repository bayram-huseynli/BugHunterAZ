package com.example.bughunteraz.service;

import com.example.bughunteraz.dto.request.CompanyRequest;
import com.example.bughunteraz.dto.request.HackerRequest;
import com.example.bughunteraz.entity.Role;
import com.example.bughunteraz.entity.User;
import com.example.bughunteraz.repository.RoleRepository;
import com.example.bughunteraz.repository.UserRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final ModelMapper modelMapper;

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();

    @Override
    public User registerHacker(HackerRequest hackerDTO) {
        User hacker = modelMapper.map(hackerDTO, User.class);
        hacker.setPassword(passwordEncoder.encode(hacker.getPassword()));

        Role hackerRole = roleRepository.findByName("ROLE_HACKER");
        if (hackerRole == null) {
            hackerRole = new Role();
            hackerRole.setName("ROLE_HACKER");
            roleRepository.save(hackerRole);
        }
        hacker.setRole(hackerRole);

        GoogleAuthenticatorKey key = gAuth.createCredentials();
        hacker.setTwoFactorSecret(key.getKey());

        User registeredHacker = userRepository.save(hacker);

        emailService.sendSimpleMessage(hacker.getEmail(), "Registration Confirmation",
                "You have successfully registered as a hacker. Your 2FA secret is: " + key.getKey());

        return registeredHacker;
    }

    @Override
    public User registerCompany(CompanyRequest companyDTO) {
        User company = modelMapper.map(companyDTO, User.class);
        company.setPassword(passwordEncoder.encode(company.getPassword()));

        Role companyRole = roleRepository.findByName("ROLE_COMPANY");
        if (companyRole == null) {
            companyRole = new Role();
            companyRole.setName("ROLE_COMPANY");
            roleRepository.save(companyRole);
        }
        company.setRole(companyRole);

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