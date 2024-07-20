package az.bughunteraz.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendRegistrationEmail(String toEmail, String token, int code) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Registration Confirmation");
        message.setText("Thank you for registration. " +
                        "Please confirm your registration using the following token: " + token + "\n" +
                        "Your 2FA Code: " + code);

        mailSender.send(message);
    }

    public void sendUpdateEmail(String toEmail) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Update Successfully");
        message.setText("Your update was successfully. Thank you.");

        mailSender.send(message);
    }

    public void send2FaCodeEmail(String toEmail, int code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Your 2FA Code");
        message.setText("Your 2FA Code is: " + code);

        mailSender.send(message);
    }

    public void sendPasswordResetEmail(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("To reset your password, please click the following link: " + resetLink);
        mailSender.send(message);
    }

}
