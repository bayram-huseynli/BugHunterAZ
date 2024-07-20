package az.bughunteraz.dto.request.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResetPasswordRequest {

    String token; // Şifre sıfırlama bağlantısından gelen token
    String newPassword;
    String confirmNewPassword; // Şifre doğrulama

}
