package az.bughunteraz.service.user;

import az.bughunteraz.dto.UserDto;
import az.bughunteraz.dto.response.UserResponse;
import az.bughunteraz.entity.Role;
import az.bughunteraz.entity.User;
import az.bughunteraz.exception.CustomException;
import az.bughunteraz.jwt.JwtTokenProvider;
import az.bughunteraz.repository.UserRepository;
import az.bughunteraz.service.email.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        String confirmPassword = userDto.getConfirmPassword();

        if (userRepository.existsByEmail(email)) {
            throw new CustomException("Email already in use");
        }

        if (!password.equals(confirmPassword)) {
            throw new CustomException("Passwords do not match");
        }

        User user = modelMapper.map(userDto, User.class);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(password));

        // Yeni 2FA kodu oluştur ve kullanıcıya ata
        int code = twoFactorAuthService.generate2FACode(email);
        user.setCode(code);
        user = userRepository.save(user);

        // JWT token oluştur ve kayıt e-posta'sı gönder
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRole());
        emailService.sendRegistrationEmail(user.getEmail(), token, code);

        return modelMapper.map(user, UserResponse.class);
    }

    @Transactional
    public void resend2FACode(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Yeni 2FA kodu oluştur ve kullanıcıya ata
        int newCode = twoFactorAuthService.generate2FACode(email);
        user.setCode(newCode);
        userRepository.save(user);

        emailService.send2FaCodeEmail(user.getEmail(), newCode);
    }

    @Override
    public void delete(Long id, String rawPassword){

        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User Not Found"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new CustomException("Incorrect Password");
        }

        userRepository.delete(user);

    }
}
