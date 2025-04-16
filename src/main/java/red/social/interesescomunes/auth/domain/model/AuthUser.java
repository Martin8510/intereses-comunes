package red.social.interesescomunes.auth.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.auth.domain.event.IAuthUserDomainEventPublisher;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser {
    private String userName;
    private String password;
    private String token;

    public void singIn(IAuthUserDomainEventPublisher eventPublisher){
        eventPublisher.publisAuthUserSingIn(this);
    }

    public void singOut(IAuthUserDomainEventPublisher eventPublisher){
        eventPublisher.publisAuthUserSingOut(this);
    }

}
