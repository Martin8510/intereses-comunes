package red.social.interesescomunes.security.service.token.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import red.social.interesescomunes.security.config.JwtConfig;
import red.social.interesescomunes.security.jwt.api.ITokenComponentsProvider;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements ITokenComponentsProvider {
    private final JwtConfig jwtConfig;
    private final long  currentTimeMillis = System.currentTimeMillis(); // Obtiene el tiempo actual en milisegundos

    public TokenServiceImpl(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String getUsername(Authentication authentication) {
        if (authentication == null || authentication.getPrincipal() == null) {
            throw new IllegalStateException("Authentication principal is null");
        }
        return  authentication.getPrincipal().toString();
    }

    @Override
    public String getTokenIssuer() {
        return  this.jwtConfig.getTokenIssuer();
    }

    @Override
    public Date getIssuedAt() {
        return new Date(currentTimeMillis);
    }

    @Override
    public Date getExpirationDate() {
        long currentTimeMillis = System.currentTimeMillis();
        return new Date(currentTimeMillis + Long.parseLong(this.jwtConfig.getExpiration()));
    }

    @Override
    public String generateTokenIdentifier() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()         // Obtiene las autoridades del usuario
                .map(GrantedAuthority::getAuthority)            // Extrae las autoridades como cadenas
                .collect(Collectors.joining(","));      // Une las autoridades en una sola cadena separada por comas
    }

    @Override
    public String extractUserNameFromToken(DecodedJWT decodedJwt) {
        return decodedJwt.getSubject().toString();
    }

    @Override
    public Claim getSpecificClaim(DecodedJWT decodedJwt, String claimName) {
        return decodedJwt.getClaim(claimName);
    }

    @Override
    public Map<String, Claim> getAllClaims(DecodedJWT decodedJwt) {
        return decodedJwt.getClaims();
    }

    @Override
    public Algorithm getSigningAlgorithm() {
        return Algorithm.HMAC256( this.jwtConfig.getSecret() );     // Crea una clave de firma HMAC;
    }

    @Override
    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        Algorithm signingKey = this.getSigningAlgorithm();
        JWTVerifier jwtVerifier = JWT.require(signingKey)
                .withIssuer(this.jwtConfig.getTokenIssuer())
                .build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }
}
