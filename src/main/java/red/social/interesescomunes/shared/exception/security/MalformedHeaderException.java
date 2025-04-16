package red.social.interesescomunes.shared.exception.security;

import red.social.interesescomunes.shared.exception.http.BadRequestException;
import red.social.interesescomunes.utils.ErrorCatalog;

/*
    Este error ocurre cuando un encabezado HTTP tiene un formato incorrecto
*/
public class MalformedHeaderException  extends BadRequestException {
    private static final String code = ErrorCatalog.MALFORMED_HEADER.getCode();
    private static final String message = ErrorCatalog.MALFORMED_HEADER.getMessage();

    public  MalformedHeaderException(String detail, String code) {
        super(message, detail, code);
    }

}
