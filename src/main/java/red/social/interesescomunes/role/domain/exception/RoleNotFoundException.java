package red.social.interesescomunes.role.domain.exception;

import red.social.interesescomunes.shared.exception.http.NotFoundException;
import red.social.interesescomunes.utils.ErrorCatalog;

/**
 * Se lanza una excepción cuando una búsqueda de rol no se encuentra.
 */
public class RoleNotFoundException extends NotFoundException {
    private static final String code = ErrorCatalog.ROLE_NOT_FOUND.getCode();
    private static final String message = ErrorCatalog.ROLE_NOT_FOUND.getMessage();

    public RoleNotFoundException(String detail){
        super(message, detail, code);
    }

}
