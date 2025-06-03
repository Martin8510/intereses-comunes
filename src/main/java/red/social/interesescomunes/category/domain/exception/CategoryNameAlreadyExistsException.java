package red.social.interesescomunes.category.domain.exception;

import red.social.interesescomunes.shared.exception.validation.ConflictException;
import red.social.interesescomunes.utils.ErrorCatalog;

public class CategoryNameAlreadyExistsException extends ConflictException {
    private static final String code = ErrorCatalog.CATEGORY_ALREADY_EXISTS.getCode();
    private static final String message = ErrorCatalog.CATEGORY_ALREADY_EXISTS.getMessage();

    public CategoryNameAlreadyExistsException(String detail) {
        super(message, detail, code);
    }
}
