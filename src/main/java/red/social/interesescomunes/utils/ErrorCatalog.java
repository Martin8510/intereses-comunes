package red.social.interesescomunes.utils;

public enum ErrorCatalog  {
    ROLE_NOT_FOUND("ERROR_ROLE_001", "Role not found."),
    ROLENAME_ALREADY_EXISTS("ERROR_ROLE_002", "El rol ya existe."),
    INVALID_ROLE("ERROR_ROLE_003", "Parámetros de rol no válidos."),
    GENERIC_ERROR("ERROR_GEN_001", "Se produjo un error inesperado."),

    CATEGORY_NOT_FOUND("ERROR_CATEGORY_001", "Categoría no encontrada."),
    MEMBER_GROUP_NOT_FOUND("ERROR_MEMBER_GROUP_001", "El miembro no se encuentra en este grupo."),

    EVENT_NOT_FOUND("ERROR_EVENT_001", "Evento de grupo no encontrada."),
    EVENT_ALREADY_EXISTS("ERROR_EVENT_002", "El evento ya existe."),
    INVALID_EVENT("ERROR_EVENT_003", "Parámetros de evento inválidos"),

    EVENT_ATTENDEE_NOT_FOUND("EA-404", "Asistencia a evento no encontrada"),
    EVENT_ATTENDEE_ALREADY_EXISTS("EA-409", "El miembro ya está registrado en este evento"),

    GROUP_NOT_FOUND("ERROR_GROUP_001", "Grupo no encontrado."),
    INVALID_GROUP("ERROR_GROUP_002", "Parámetros de grupo no válidos."),
    GROUP_ALREADY_EXISTS("ERROR_GROUP_003", "El grupo con este nombre ya existe."),

    USER_NOT_FOUND("ERROR_USER_001", "Usuario no encontrado."),
    EMAIL_ALREADY_EXISTS("ERROR_USER_002", "Correo electrónico ya registrado."),
    INVALID_USER("ERROR_USER_005", "Parámetros de usuario no válidos."),
    USERNAME_ALREADY_EXISTS("ERROR_USER_002", "El nombre de usuario ya está en uso"),
    CATEGORY_ALREADY_EXISTS("ERROR_CATEGORY_002", "Ya existe una categoría con este nombre"),

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
