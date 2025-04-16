package red.social.interesescomunes.shared.exception.validation;

/*
    Se utiliza cuando la solicitud no puede ser completada
    debido a un conflicto con el estado actual del recurso.
*/
public class ConflictException extends RuntimeException{
    private static final String DESCRIPTION = "Conflict Exception (409) :";
    private final String code;
    private final String detail;

    public ConflictException(String message,String detail, String code){
        super(DESCRIPTION + message);
        this.code = code;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public String getDetail() {
        return this.detail;
    }
}
