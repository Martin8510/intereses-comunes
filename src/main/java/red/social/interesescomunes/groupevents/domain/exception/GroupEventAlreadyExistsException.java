package red.social.interesescomunes.groupevents.domain.exception;

import red.social.interesescomunes.shared.exception.validation.ConflictException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class GroupEventAlreadyExistsException extends ConflictException {
    private static final String code = ErrorCatalog.EVENT_ALREADY_EXISTS.getCode();
    private static final String message = ErrorCatalog.EVENT_ALREADY_EXISTS.getMessage();

    public GroupEventAlreadyExistsException(String detail) {
        super(message, detail, code);
    }
}