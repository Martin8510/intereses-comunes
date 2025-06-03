package red.social.interesescomunes.eventattendee.domain.exception;

import red.social.interesescomunes.shared.exception.http.NotFoundException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class EventAttendeeNotFoundException extends NotFoundException {
    private static final String code = ErrorCatalog.CATEGORY_NOT_FOUND.getCode();
    private static final String message = ErrorCatalog.CATEGORY_NOT_FOUND.getMessage();

    public EventAttendeeNotFoundException(String detail) {
        super(message, detail, code);
    }
}