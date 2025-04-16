package red.social.interesescomunes.user.domain.exception;

import red.social.interesescomunes.shared.exception.http.NotFoundException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class UserNotFoundException extends NotFoundException {
    private static final String code = ErrorCatalog.USER_NOT_FOUND.getCode();
    private static final String message = ErrorCatalog.USER_NOT_FOUND.getMessage();

    public UserNotFoundException(String detail){
        super(message, detail, code);
    }
}
