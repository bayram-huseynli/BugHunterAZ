package com.example.bughunteraz.service;

import com.example.bughunteraz.dto.CompanyDto;
import com.example.bughunteraz.dto.HackerDto;
import com.example.bughunteraz.dto.response.UserResponse;
import com.example.bughunteraz.entity.Role;
import com.example.bughunteraz.entity.User;
import com.example.bughunteraz.exception.CustomException;
import com.example.bughunteraz.jwt.JwtTokenProvider;
import com.example.bughunteraz.repository.UserRepository;
import com.example.bughunteraz.service.email.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final EmailService emailService;

    @Transactional
    public UserResponse registerHacker(HackerDto hackerDto) {
        if (userRepository.existsByEmail(hackerDto.getEmail())) {
            throw new CustomException("Email already in use");
        }

        User user = modelMapper.map(hackerDto, User.class);
        user.setRole(Role.HACKER);
        user.setPassword(passwordEncoder.encode(hackerDto.getPassword()));
        user = userRepository.save(user);

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        emailService.sendRegistrationEmail(user.getEmail(), token);

        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    public UserResponse registerCompany(CompanyDto companyDto) {
        if (userRepository.existsByEmail(companyDto.getEmail())) {
            throw new CustomException("Email already in use");
        }

        User user = modelMapper.map(companyDto, User.class);
        user.setRole(Role.COMPANY);
        user.setPassword(passwordEncoder.encode(companyDto.getPassword()));
        user = userRepository.save(user);

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        emailService.sendRegistrationEmail(user.getEmail(), token);

        return modelMapper.map(user, UserResponse.class);
    }
}