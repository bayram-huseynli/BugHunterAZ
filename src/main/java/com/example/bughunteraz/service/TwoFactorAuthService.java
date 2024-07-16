package com.example.bughunteraz.service;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.warrenstrange.googleauth.IGoogleAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TwoFactorAuthService {


    private final IGoogleAuthenticator gAuth = new GoogleAuthenticator();

    public GoogleAuthenticatorKey generateSecretKey() {
        return gAuth.createCredentials();
    }

    public String generateQRCodeURL(String email, GoogleAuthenticatorKey secret) {
        try {
            return GoogleAuthenticatorQRGenerator.getOtpAuthURL("BugHunter", email, secret);
        } catch (Exception e) {
            throw new RuntimeException("Error generating QR Code URL", e);
        }
    }

    public boolean verifyCode(String secret, int code) {
        return gAuth.authorize(secret, code);
    }

    public int getCurrentCode(String secret) {
        return gAuth.getTotpPassword(secret);
    }
}
