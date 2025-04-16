package red.social.interesescomunes.auth.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.auth.domain.event.AuthUserSignInEvent;
import red.social.interesescomunes.auth.domain.event.AuthUserSignOutEvent;
import red.social.interesescomunes.auth.domain.event.IAuthUserDomainEventPublisher;
import red.social.interesescomunes.auth.domain.model.AuthUser;

@Component
public class SpringAuthUserEventPublisherImpl implements IAuthUserDomainEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public SpringAuthUserEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void publisAuthUserSingIn(AuthUser authUser) {
        this.eventPublisher.publishEvent(new AuthUserSignInEvent(authUser));
    }

    @Override
    public void publisAuthUserSingOut(AuthUser authUser) {
        this.eventPublisher.publishEvent(new AuthUserSignOutEvent(authUser));
    }
}
