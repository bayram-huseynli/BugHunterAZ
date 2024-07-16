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

    public void sendRegistrationEmail(String toEmail, String token, String secret, int code) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Registration Confirmation");
        message.setText("Please confirm your registration using the following token: " + token + "\n" +
                        "Your Secret Key: " + secret + "\n" +
                        "Your 2FA Code: " + code);

        mailSender.send(message);
    }
}
