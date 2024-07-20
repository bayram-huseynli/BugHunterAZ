package az.bughunteraz.controller.auth;

import az.bughunteraz.dto.request.auth.LoginRequest;
import az.bughunteraz.dto.request.auth.Resend2FaRequest;
import az.bughunteraz.dto.response.auth.AuthenticationResponse;
import az.bughunteraz.entity.User;
import az.bughunteraz.exception.CustomException;
import az.bughunteraz.jwt.JwtTokenProvider;
import az.bughunteraz.repository.UserRepository;
import az.bughunteraz.service.user.TwoFactorAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final TwoFactorAuthService twoFactorAuthService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new CustomException("User Not Found"));

        boolean is2FAVerified = twoFactorAuthService.verifyCode(user.getEmail(), loginRequest.getCode());
        if (!is2FAVerified) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid 2FA code");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication.getName(), user.getRole());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/resend-2fa-code")
    public ResponseEntity<?> resend2FACode(@RequestBody Resend2FaRequest resend2FaRequest) {
        try {
            User user = userRepository.findByEmail(resend2FaRequest.getEmail())
                    .orElseThrow(() -> new CustomException("User Not Found"));

            // Yeni kod oluşturulması ve e-posta ile gönderilmesi
            twoFactorAuthService.resend2FaCode(user.getEmail());

            return ResponseEntity.ok("2FA code resent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to resend 2FA code");
        }
    }
}
