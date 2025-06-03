package red.social.interesescomunes.groupevents.domain.exception;

import red.social.interesescomunes.shared.exception.http.NotFoundException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class GroupEventNotFoundException extends NotFoundException {
    private static final String code = ErrorCatalog.EVENT_NOT_FOUND.getCode();
    private static final String message = ErrorCatalog.EVENT_NOT_FOUND.getMessage();

    public GroupEventNotFoundException(String detail) {
        super(message, detail, code);
    }
}