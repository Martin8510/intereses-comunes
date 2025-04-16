package red.social.interesescomunes.shared.exception.security;

/*
    Indica que se requiere autenticacion para acceder al recurso, pero no
    proporcionaron credenciales validas.
*/
public class UnauthorizedException extends RuntimeException{
    private static final String DESCRIPTION = "unautorized Exception (401)";

    public UnauthorizedException(String detail){
        super(DESCRIPTION + ": " + detail);
    }
}