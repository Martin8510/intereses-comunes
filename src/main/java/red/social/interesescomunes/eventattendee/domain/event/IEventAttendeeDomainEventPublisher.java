package red.social.interesescomunes.eventattendee.domain.event;

import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;

public interface IEventAttendeeDomainEventPublisher {
    void publishEventAttendeeCreated(EventAttendee eventAttendee);
    void publishEventAttendeeUpdated(EventAttendee eventAttendee);
}