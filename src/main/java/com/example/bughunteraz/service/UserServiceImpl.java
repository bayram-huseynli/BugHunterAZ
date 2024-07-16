package com.example.bughunteraz.service;

import com.example.bughunteraz.dto.UserDto;
import com.example.bughunteraz.dto.response.UserResponse;
import com.example.bughunteraz.entity.Role;
import com.example.bughunteraz.entity.User;
import com.example.bughunteraz.exception.CustomException;
import com.example.bughunteraz.jwt.JwtTokenProvider;
import com.example.bughunteraz.repository.UserRepository;
import com.example.bughunteraz.service.email.EmailService;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final EmailService emailService;

    private final TwoFactorAuthService twoFactorAuthService;

    @Override
    @Transactional
    public UserResponse registerUser(UserDto userDto, Role role) {

        String email = userDto.getEmail();
        String password = userDto.getPassword();

        if (userRepository.existsByEmail(email)) {
            throw new CustomException("Email already in use");
        }

        final GoogleAuthenticatorKey key = twoFactorAuthService.generateSecretKey();

        User user = modelMapper.map(userDto, User.class);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(password));
        user.setSecret(key.getKey());
        user = userRepository.save(user);

        int code = twoFactorAuthService.getCurrentCode(key.getKey());

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        String qrCodeUrl = twoFactorAuthService.generateQRCodeURL(user.getEmail(), key);
        emailService.sendRegistrationEmail(user.getEmail(), token, qrCodeUrl, code);

        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setQrCodeUrl(qrCodeUrl);
        return userResponse;
    }
}

