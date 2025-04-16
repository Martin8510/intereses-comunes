package red.social.interesescomunes.shared.exception.http;

/*
    Ocurre cuando la solicitud enviada al servidor es incorrecta o malformada.
*/
public class BadRequestException extends RuntimeException{
    private static final String DESCRIPTION = "Bad Request Exception (400)";
    private final String code;
    private final String detail;

    public BadRequestException(String message, String detail, String code){
        super(DESCRIPTION + ": " + message);
        this.code = code;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }
}
