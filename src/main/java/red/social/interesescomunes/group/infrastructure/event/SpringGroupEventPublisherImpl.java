package red.social.interesescomunes.group.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.group.domain.event.GroupCreatedEvent;
import red.social.interesescomunes.group.domain.event.GroupDeletedEvent;
import red.social.interesescomunes.group.domain.event.GroupUpdatedEvent;
import red.social.interesescomunes.group.domain.event.IGroupDomainEventPublisher;
import red.social.interesescomunes.group.domain.model.Group;

@Component
public class SpringGroupEventPublisherImpl implements IGroupDomainEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public SpringGroupEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishGroupCreated(Group group) {
        this.eventPublisher.publishEvent(new GroupCreatedEvent(group));
    }

    @Override
    public void publishGroupUpdated(Group group) {
        eventPublisher.publishEvent(new GroupUpdatedEvent(group));
    }

    @Override
    public void publishGroupDeleted(Group group) {
        eventPublisher.publishEvent(new GroupDeletedEvent(group));
    }
}
