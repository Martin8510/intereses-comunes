package red.social.interesescomunes.shared.exception.http;

/*
    Significa que el recurso solicitado no pudo ser encontrado en el servidor.
*/
public class NotFoundException extends RuntimeException{
    private static final String DESCRIPTION = "Not Found Exception (404) :";
    private final String code;
    private final String detail;


    public NotFoundException(String message, String detail, String code){
        super(DESCRIPTION + message);
        this.code = code;
        this.detail = detail;
    }

    public String getCode() {
        return this.code;
    }

    public String getDetail() {
        return this.detail;
    }
}
