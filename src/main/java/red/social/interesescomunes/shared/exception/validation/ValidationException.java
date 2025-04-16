package red.social.interesescomunes.shared.exception.validation;

import red.social.interesescomunes.shared.exception.http.BadRequestException;
import red.social.interesescomunes.shared.exception.model.FieldErrorDetail;
import red.social.interesescomunes.utils.ErrorCatalog;

import java.util.List;

public class ValidationException extends BadRequestException {
    private static final String code = ErrorCatalog.VALIDATION_ERROR.getCode();
    private static final String message = ErrorCatalog.VALIDATION_ERROR.getMessage();
    private final List<FieldErrorDetail> fieldErrors;

    public ValidationException(List<FieldErrorDetail> fieldErrors) {
        super(message, "", code);
        this.fieldErrors = fieldErrors;
    }

    public List<FieldErrorDetail> getFieldErrors() {
        return fieldErrors;
    }
}