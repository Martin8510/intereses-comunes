package red.social.interesescomunes.security.jwt.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.security.jwt.api.ITokenComponentsProvider;
import red.social.interesescomunes.security.jwt.api.ITokenGenerator;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtilsImpl implements ITokenComponentsProvider, ITokenGenerator {
    private final ITokenComponentsProvider tokenService;

    public JwtUtilsImpl(ITokenComponentsProvider tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public String generateJwtToken(Authentication authentication) {
        String username = this.getUsername(authentication);
        Date issuedAt = this.getIssuedAt(); // Fecha de
        Date expirationDate = this.getExpirationDate();
        String tokenId = this.generateTokenIdentifier();
        String authorities = this.getAuthorities(authentication);
        String tokenIssuer = this.getTokenIssuer();
        Algorithm signingKey = this.getSigningAlgorithm();

        String JwtToken =  JWT.create()
            .withIssuer(tokenIssuer)
            .withSubject(username)
            .withClaim("authorities", authorities)
            .withIssuedAt(issuedAt)
            .withExpiresAt(expirationDate)
            .withJWTId(tokenId)
            .withNotBefore(issuedAt)
            .sign(signingKey);
        return JwtToken;
    }

    @Override
    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        return this.tokenService.validateToken(token);
    }

    @Override
    public String getUsername(Authentication authentication) {
        return this.tokenService.getUsername(authentication);
    }

    @Override
    public String getTokenIssuer() {
        return this.tokenService.getTokenIssuer();
    }

    @Override
    public Date getIssuedAt() {
        return this.tokenService.getIssuedAt();
    }

    @Override
    public Date getExpirationDate() {
        return this.tokenService.getExpirationDate();
    }

    @Override
    public String generateTokenIdentifier() {
        return this.tokenService.generateTokenIdentifier();
    }

    @Override
    public String getAuthorities(Authentication authentication) {
        return this.tokenService.getAuthorities(authentication);
    }

    @Override
    public String extractUserNameFromToken(DecodedJWT decodedJwt) {
        return this.tokenService.extractUserNameFromToken(decodedJwt);
    }

    @Override
    public Claim getSpecificClaim(DecodedJWT decodedJwt, String claimName) {
        return this.tokenService.getSpecificClaim(decodedJwt,claimName);
    }

    @Override
    public Map<String, Claim> getAllClaims(DecodedJWT decodedJwt) {
        return this.tokenService.getAllClaims(decodedJwt);
    }

    @Override
    public Algorithm getSigningAlgorithm() {
        return this.tokenService.getSigningAlgorithm();
    }
}
