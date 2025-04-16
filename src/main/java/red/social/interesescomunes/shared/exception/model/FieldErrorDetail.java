package red.social.interesescomunes.shared.exception.model;

public class FieldErrorDetail {
    private final String field;
    private final String code;
    private final String message;

    public FieldErrorDetail(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }

    // Getters
    public String getField() { return field; }
    public String getCode() { return code; }
    public String getMessage() { return message; }
}