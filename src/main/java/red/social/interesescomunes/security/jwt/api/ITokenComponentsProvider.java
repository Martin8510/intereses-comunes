package red.social.interesescomunes.security.jwt.api;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Map;

/**
 * Define las operaciones necesarias para la generación de tokens JWT
 */
public interface ITokenComponentsProvider{
    /**
     * Extrae el nombre de usuario del objeto de autenticación
     * @param authentication Objeto de autenticación de Spring Security
     * @return Nombre de usuario
     */
    String getUsername(Authentication authentication);
    // Emisor del token
    String getTokenIssuer();
    /**
     * Obtiene la fecha actual o de expiración para el token
     * @return Fecha generada
     */
    Date getIssuedAt();
    /**
     * Obtiene la fecha actual, o la fecha de expiración si es necesario.
     * @return La fecha actual o de expiración.
     */
    Date getExpirationDate();
    /**
     * Genera un identificador único para el token
     * @return Identificador único en formato String
     */
    String generateTokenIdentifier();
    /**
     * Extrae los roles/permisos del usuario
     * @param authentication Objeto de autenticación de Spring Security
     * @return Colección de autoridades como String delimitado
     */
    String getAuthorities(Authentication authentication);
    /**
     * Extrae el nombre de usuario desde el token decodificado.
     *
     * @param decodedJwt El token decodificado.
     * @return El nombre de usuario extraído del token.
     */
    String extractUserNameFromToken(DecodedJWT decodedJwt);
    /**
     * Obtiene un claim específico de un token decodificado.
     *
     * @param decodedJwt El token decodificado.
     * @param claimName El nombre del claim que se quiere obtener.
     * @return El claim solicitado.
     */
     Claim getSpecificClaim(DecodedJWT decodedJwt, String claimName);
    /**
     * Obtiene todos los claims de un token decodificado.
     *
     * @param decodedJwt El token decodificado.
     * @return Un mapa con todos los claims del token.
     */
     Map<String,Claim> getAllClaims(DecodedJWT decodedJwt);
    /**
     * Obtiene el algoritmo de firma para el token
     * @return Algoritmo de firma configurado
     */
    Algorithm getSigningAlgorithm();

    DecodedJWT validateToken(String token) throws JWTVerificationException;
}
