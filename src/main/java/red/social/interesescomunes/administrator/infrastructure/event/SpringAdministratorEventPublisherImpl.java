package red.social.interesescomunes.administrator.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.administrator.domain.event.AdministratorCreatedEvent;
import red.social.interesescomunes.administrator.domain.event.AdministratorDeletedEvent;
import red.social.interesescomunes.administrator.domain.event.AdministratorUpdatedEvent;
import red.social.interesescomunes.administrator.domain.event.IAdministratorDomainEventPublisher;
import red.social.interesescomunes.administrator.domain.model.Administrator;

@Component
public class SpringAdministratorEventPublisherImpl implements IAdministratorDomainEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public SpringAdministratorEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishAdministratorCreated(Administrator administrator) {
        eventPublisher.publishEvent(new AdministratorCreatedEvent(administrator));
    }

    @Override
    public void publishAdministratorUpdated(Administrator administrator) {
        eventPublisher.publishEvent(new AdministratorUpdatedEvent(administrator));
    }

    @Override
    public void publishAdministratorDeleted(Administrator administrator) {
        eventPublisher.publishEvent(new AdministratorDeletedEvent(administrator));
    }
}
