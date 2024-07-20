package az.bughunteraz.service.user;

import az.bughunteraz.repository.UserRepository;
import az.bughunteraz.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TwoFactorAuthService {


    private final EmailService emailService;
    private final UserRepository userRepository;

    // Kullanıcı e-posta adreslerine göre geçici olarak kodları saklamak için bir harita kullanıyoruz
    private final Map<String, Integer> codeMap = new HashMap<>();
    private final Map<String, Long> codeTimestampMap = new HashMap<>();
    private static final int CODE_VALID_DURATION = 5; // Kodun geçerlilik süresi dakika cinsinden

    public int generate2FACode(String email) {
        int code = new Random().nextInt(900000) + 100000; // 100000 ile 999999 arasında bir sayı
        codeMap.put(email, code);
        codeTimestampMap.put(email, System.currentTimeMillis());
        return code;
    }

    public boolean verifyCode(String email, int inputCode) {
        if (!codeMap.containsKey(email)) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long codeGenerationTime = codeTimestampMap.get(email);
        if (currentTimeMillis - codeGenerationTime > TimeUnit.MINUTES.toMillis(CODE_VALID_DURATION)) {
            codeMap.remove(email);
            codeTimestampMap.remove(email);
            return false;
        }
        return codeMap.get(email) == inputCode;
    }

    public void resend2FaCode(String email) {
        int code = generate2FACode(email);
        emailService.send2FaCodeEmail(email, code);
    }
}