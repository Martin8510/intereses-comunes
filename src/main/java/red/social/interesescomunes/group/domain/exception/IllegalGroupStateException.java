package red.social.interesescomunes.group.domain.exception;

import red.social.interesescomunes.shared.exception.http.BadRequestException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class IllegalGroupStateException extends BadRequestException {
    private static final String code = ErrorCatalog.INVALID_GROUP.getCode();
    private static final String message = ErrorCatalog.INVALID_GROUP.getMessage();

    public IllegalGroupStateException(String detail) {
        super(message, detail, code);
    }
}
