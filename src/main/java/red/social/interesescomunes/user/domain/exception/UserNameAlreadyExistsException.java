package red.social.interesescomunes.user.domain.exception;

import red.social.interesescomunes.shared.exception.validation.ConflictException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class UserNameAlreadyExistsException extends ConflictException {
    private static final String code = ErrorCatalog.USERNAME_ALREADY_EXISTS.getCode();
    private static final String message = ErrorCatalog.USERNAME_ALREADY_EXISTS.getMessage();

    public UserNameAlreadyExistsException(String detail) {
        super(message, detail, code);
    }
}
