package red.social.interesescomunes.security.service.api;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.request.AuthUserRequest;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;
import red.social.interesescomunes.user.domain.exception.UserNotFoundException;
import red.social.interesescomunes.user.infrastructure.output.persistence.mysql.entity.UserEntity;

import javax.naming.AuthenticationException;
import java.util.List;

public interface IAuthSecurityService extends UserDetailsService {
    /**
     * Autentica usuario y genera token JWT
     */
    AuthUserResponse authenticateAndGenerateToken(AuthUserRequest request) throws AuthenticationException;
    /**
     * Valida credenciales básicas
     */
    Authentication verifyCredentials(String userName, String password) throws BadCredentialsException;
    /**
     * Obtiene usuario por identificador
     */
    UserEntity getUserByIdentifier(String userName) throws UserNotFoundException;
    /**
     * Obtiene usuario details de security
     */
    UserDetails getUserDetail(UserEntity userEntity );
    /**
     * Convierte roles y permisos a autoridades Spring Security
     */
    List<SimpleGrantedAuthority> convertToGrantedAuthorities(UserEntity user);
    /**
     * Genera token para usuario registrado
     */
    AuthUserResponse generatePostRegistrationToken(String userName);
}