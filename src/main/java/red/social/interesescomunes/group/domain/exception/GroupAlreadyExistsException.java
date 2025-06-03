package red.social.interesescomunes.group.domain.exception;

import red.social.interesescomunes.shared.exception.validation.ConflictException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class GroupAlreadyExistsException extends ConflictException {
    private static final String code = ErrorCatalog.GROUP_ALREADY_EXISTS.getCode();
    private static final String message = ErrorCatalog.GROUP_ALREADY_EXISTS.getMessage();

    public GroupAlreadyExistsException(String detail) {
        super(message, detail, code);
    }
}