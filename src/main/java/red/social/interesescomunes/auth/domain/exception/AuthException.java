package red.social.interesescomunes.auth.domain.exception;

import red.social.interesescomunes.shared.exception.security.UnauthorizedException;

public class AuthException extends UnauthorizedException {
    public AuthException(String message) {
        super(message);
    }
}