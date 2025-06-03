package red.social.interesescomunes.eventattendee.domain.exception;

import red.social.interesescomunes.shared.exception.validation.ConflictException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class EventAttendeeAlreadyExistsException extends ConflictException {
    private static final String code = ErrorCatalog.EVENT_ATTENDEE_NOT_FOUND.getCode();
    private static final String message = ErrorCatalog.EVENT_ATTENDEE_NOT_FOUND.getMessage();

    public EventAttendeeAlreadyExistsException(String detail) {
        super(message, detail, code);
    }
}
