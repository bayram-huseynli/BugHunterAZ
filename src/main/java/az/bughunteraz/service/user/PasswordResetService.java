package az.bughunteraz.service.user;

import az.bughunteraz.dto.request.auth.ForgotPasswordRequest;
import az.bughunteraz.dto.request.auth.ResetPasswordRequest;
import az.bughunteraz.entity.User;
import az.bughunteraz.exception.CustomException;
import az.bughunteraz.repository.UserRepository;
import az.bughunteraz.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    @Transactional
    public void initiatePasswordReset(ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByEmail(forgotPasswordRequest.getEmail())
                .orElseThrow(() -> new CustomException("User not found"));

        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user);

        String resetLink = "https://your-app-url/reset-password?token=" + token;
        emailService.sendPasswordResetEmail(user.getEmail(), resetLink);
    }

    @Transactional
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmNewPassword())) {
            throw new CustomException("Passwords do not match");
        }

        User user = userRepository.findByResetToken(resetPasswordRequest.getToken())
                .orElseThrow(() -> new CustomException("Invalid or expired token"));

        user.setPassword(resetPasswordRequest.getNewPassword());
        user.setResetToken(null); // Token kullanılmaktan sonra silinir
        userRepository.save(user);
    }
}
