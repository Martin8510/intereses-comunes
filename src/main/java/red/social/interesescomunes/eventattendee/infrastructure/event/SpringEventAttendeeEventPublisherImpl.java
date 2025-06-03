package red.social.interesescomunes.eventattendee.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.eventattendee.domain.event.EventAttendeeCreatedEvent;
import red.social.interesescomunes.eventattendee.domain.event.EventAttendeeUpdatedEvent;
import red.social.interesescomunes.eventattendee.domain.event.IEventAttendeeDomainEventPublisher;
import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;

@Component
public class SpringEventAttendeeEventPublisherImpl implements IEventAttendeeDomainEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public SpringEventAttendeeEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishEventAttendeeCreated(EventAttendee eventAttendee) {
        eventPublisher.publishEvent(new EventAttendeeCreatedEvent(eventAttendee));
    }

    @Override
    public void publishEventAttendeeUpdated(EventAttendee eventAttendee) {
        eventPublisher.publishEvent(new EventAttendeeUpdatedEvent(eventAttendee));
    }
}