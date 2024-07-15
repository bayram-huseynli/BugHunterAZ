package com.example.bughunteraz.service.email;

import com.example.bughunteraz.entity.User;
import com.example.bughunteraz.repository.UserRepository;
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


    public void sendRegistrationEmail(String toEmail, String token) {

        User user = new User();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Registration Confirmation");
        message.setText("Please confirm your registration using the following token: " + token +
                        "Your Secret Key: " + user.getSecret());

        mailSender.send(message);
    }
}
