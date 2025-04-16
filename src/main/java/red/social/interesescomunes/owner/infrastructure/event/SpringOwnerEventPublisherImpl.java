package red.social.interesescomunes.owner.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.owner.domain.event.IOwnerDomainEventPublisher;
import red.social.interesescomunes.owner.domain.event.OwnerCreatedEvent;
import red.social.interesescomunes.owner.domain.event.OwnerDeletedEvent;
import red.social.interesescomunes.owner.domain.event.OwnerUpdatedEvent;
import red.social.interesescomunes.owner.domain.model.Owner;

@Component
public class SpringOwnerEventPublisherImpl implements IOwnerDomainEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public SpringOwnerEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void publishOwnerCreated(Owner owner) {
        eventPublisher.publishEvent(new OwnerCreatedEvent(owner));
    }

    @Override
    public void publishOwnerUpdated(Owner owner) {
        eventPublisher.publishEvent(new OwnerUpdatedEvent(owner));
    }

    @Override
    public void publishOwnerDeleted(Owner owner) {
        eventPublisher.publishEvent(new OwnerDeletedEvent(owner));
    }
}
