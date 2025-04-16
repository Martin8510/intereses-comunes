package red.social.interesescomunes.role.domain.exception;

import red.social.interesescomunes.shared.exception.validation.ConflictException;
import red.social.interesescomunes.utils.ErrorCatalog;

/**
 * Se lanza una excepción al intentar crear un rol con un nombre (TypeRole) que ya existe en el sistema.
 */
public class RoleNameAlreadyExistsException extends ConflictException {
    private static final String code = ErrorCatalog.ROLENAME_ALREADY_EXISTS.getCode();
    private static final String message = ErrorCatalog.ROLENAME_ALREADY_EXISTS.getMessage();

    public RoleNameAlreadyExistsException(String detail) {
        super(message, detail, code);
    }
}
