package red.social.interesescomunes.security.config;

import lombok.Data;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@Getter
public class JwtConfig {
    @Value("${jwt.secret.key.private}")
    private String secret;

    @Value("${jwt.time.expiration}")
    private String expiration;

    @Value("${security.jwt.user.generator}")
    private String tokenIssuer;
}
