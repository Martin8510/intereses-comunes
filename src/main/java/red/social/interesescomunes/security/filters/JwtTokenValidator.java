package red.social.interesescomunes.security.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import red.social.interesescomunes.security.jwt.impl.JwtUtilsImpl;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenValidator extends OncePerRequestFilter {
    private final JwtUtilsImpl jwtUtils;

    public JwtTokenValidator(JwtUtilsImpl jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        // 1. ** obtenemos la cabecera de autorización**
        final String jwtTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!this.isValidBearerToken(jwtTokenHeader)){
            logger.trace("No JWT token found in request headers or header does not start with Bearer. Proceeding without authentication.");
            filterChain.doFilter(request, response);
            return;
        }
        // 2. Extraer el token (sabemos que la cabecera es válida en formato)
        String token = extractToken(jwtTokenHeader);
        // 3. **validación del token
        DecodedJWT decodedJWT = this.validateToken(token);
        if (decodedJWT == null) {
            logger.warn("Invalid JWT token received: {}");
            filterChain.doFilter(request, response);
            return;
        }
        // 4.  Establecer la autenticación en el contexto de seguridad
        setSecurityContextAuthentication(decodedJWT);
        //5. Continúa la cadena de filtros después de este filtro.
        filterChain.doFilter(request, response);
    }

    private boolean isValidBearerToken(String header) {
        return header != null && header.startsWith("Bearer ");
    }

    private String extractToken(String bearerToken) {
        return bearerToken.substring(7);
    }


    private DecodedJWT validateToken(String token) {
        try {
            DecodedJWT decodedJWT = this.jwtUtils.validateToken(token);
            if (decodedJWT.getSubject() == null) {
                logger.error("Token validation failed: missing subject");
                return null;
            }
            return decodedJWT;
        } catch (JWTVerificationException e) {
            logger.error("Token validation failed", e);
            return null;
        }
    }

    private void setSecurityContextAuthentication(DecodedJWT decodedJWT) {
        // Extrae el nombre de usuario del token JWT.
        String userName = this.jwtUtils.extractUserNameFromToken(decodedJWT);

        if (userName == null) {
            throw new JWTVerificationException("Token does not contain a valid subject");
        }
        // Obtiene las autoridades (roles) del usuario como una cadena separada por comas.
        String authoritiesToString = this.jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();
        // Convierte la cadena de roles en una colección de GrantedAuthority.
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesToString);
        // Obtiene el contexto de seguridad actual.
        SecurityContext context = SecurityContextHolder.getContext();
        // Crea un objeto de autenticación con el nombre de usuario y las autoridades.
        Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, authorities);
        // Establece el objeto de autenticación en el contexto de seguridad.
        context.setAuthentication(authentication);
        // Configura el contexto de seguridad en SecurityContextHolder.
        SecurityContextHolder.setContext(context);
    }
}


