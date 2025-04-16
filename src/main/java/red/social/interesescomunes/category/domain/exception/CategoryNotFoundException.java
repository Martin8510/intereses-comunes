package red.social.interesescomunes.category.domain.exception;

import red.social.interesescomunes.shared.exception.http.NotFoundException;
import red.social.interesescomunes.utils.ErrorCatalog;

/**
 * Se lanza una excepción cuando una búsqueda de una categoria no se encuentra.
 */
public class CategoryNotFoundException extends NotFoundException {
    private static final String code = ErrorCatalog.CATEGORY_NOT_FOUND.getCode();
    private static final String message = ErrorCatalog.CATEGORY_NOT_FOUND.getMessage();

    public CategoryNotFoundException(String detail) {
        super(message, detail, code);
    }
}