package red.social.interesescomunes.security.jwt.api;

import org.springframework.security.core.Authentication;

public interface ITokenGenerator {
    String generateJwtToken(Authentication authentication);
}
