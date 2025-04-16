package red.social.interesescomunes.utils;

public enum ErrorCatalog  {
    ROLE_NOT_FOUND("ERROR_ROLE_001", "Role not found."),
    ROLENAME_ALREADY_EXISTS("ERROR_ROLE_002", "El rol ya existe."),
    INVALID_ROLE("ERROR_ROLE_003", "Invalid role parameters."),
    GENERIC_ERROR("ERROR_GEN_001", "An unexpected error occurred."),

    CATEGORY_NOT_FOUND("ERROR_CATEGORY_001", "Category not found."),

    GROUP_NOT_FOUND("ERROR_GROUP_001", "Group not found."),
    INVALID_GROUP("ERROR_GROUP_002", "Invalid group parameters."),
    GROUP_ALREADY_EXISTS("ERROR_GROUP_003", "Group with this name already exists."),

    USER_NOT_FOUND("ERROR_USER_001", "User not found."),
    EMAIL_ALREADY_EXISTS("ERROR_USER_002", "Email already registered."),
    INVALID_USER("ERROR_USER_005", "Invalid user parameters."),
    USERNAME_ALREADY_EXISTS("ERROR_USER_002", "El nombre de usuario ya está en uso"),

    MALFORMED_HEADER("ERROR_SECURITY_001","El encabezado HTTP tiene un formato incorrecto"),

    // Errores de validación
    VALIDATION_ERROR("ERROR_VALID_001", "Error de validación en los datos de entrada"),
    INVALID_FIELD_FORMAT("ERROR_VALID_002", "Formato de campo inválido"),
    MISSING_REQUIRED_FIELD("ERROR_VALID_003", "Campo requerido faltante"),
    FIELD_SIZE_EXCEEDED("ERROR_VALID_004", "Tamaño de campo excedido"),
    INVALID_EMAIL_FORMAT("ERROR_VALID_005", "Formato de email inválido"),
    INVALID_DATE_FORMAT("ERROR_VALID_006", "Formato de fecha inválido");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
