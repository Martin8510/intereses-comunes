package red.social.interesescomunes.shared.exception.http;

/*
  El servidor entiende la solicitud, pero se niega a cumplirla.
*/
public class ForbiddenException  extends RuntimeException{
    private static final String DESCRIPTION = "Forbidden Exception (403)";
    private final String code;

    public ForbiddenException(String detail, String code){
        super(DESCRIPTION + ": " + detail);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}