package red.social.interesescomunes.groupevents.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.groupevents.domain.event.GroupEventCancelledEvent;
import red.social.interesescomunes.groupevents.domain.event.GroupEventCreatedEvent;
import red.social.interesescomunes.groupevents.domain.event.GroupEventUpdatedEvent;
import red.social.interesescomunes.groupevents.domain.event.IGroupEventDomainEventPublisher;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;

@Component
public class SpringGroupEventEventPublisherImpl implements IGroupEventDomainEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public SpringGroupEventEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishGroupEventCreated(GroupEvent event) {
        eventPublisher.publishEvent(new GroupEventCreatedEvent(event));
    }

    @Override
    public void publishGroupEventUpdated(GroupEvent event) {
        eventPublisher.publishEvent(new GroupEventUpdatedEvent(event));
    }

    @Override
    public void publishGroupEventCancelled(GroupEvent event) {
        eventPublisher.publishEvent(new GroupEventCancelledEvent(event));
    }
}