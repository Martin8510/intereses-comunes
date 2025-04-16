package red.social.interesescomunes.auth.domain.event;

import red.social.interesescomunes.auth.domain.model.AuthUser;

public interface IAuthUserDomainEventPublisher {
    void publisAuthUserSingIn(AuthUser authUser);
    void publisAuthUserSingOut(AuthUser authUser);
}
