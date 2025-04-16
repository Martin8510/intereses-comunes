package red.social.interesescomunes.user.domain.exception;

import red.social.interesescomunes.shared.exception.http.BadRequestException;
import red.social.interesescomunes.shared.exception.validation.ConflictException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class EmailAlreadyExistsException extends ConflictException {
    private static final String code = ErrorCatalog.EMAIL_ALREADY_EXISTS.getCode();
    private static final String message = ErrorCatalog.EMAIL_ALREADY_EXISTS.getMessage();

    public EmailAlreadyExistsException(String detail) {
        super(message, detail, code);
    }
}
