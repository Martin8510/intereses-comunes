package red.social.interesescomunes.security.service.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordVerificationService {
    private final PasswordEncoder passwordEncoder;

    public BCryptPasswordVerificationService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public boolean validatePassword(String passwordUser, String passwordBd) {
        return passwordEncoder.matches(passwordUser, passwordBd);
    }
}
