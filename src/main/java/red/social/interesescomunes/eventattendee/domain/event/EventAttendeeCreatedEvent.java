package red.social.interesescomunes.eventattendee.domain.event;


import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;

public class EventAttendeeCreatedEvent {
    private final EventAttendeeEventDTO eventAttendee;

    public EventAttendeeCreatedEvent(EventAttendee eventAttendee) {
        this.eventAttendee = EventAttendeeEventDTO.create(eventAttendee);
    }

    public EventAttendeeEventDTO getEventAttendee() {
        return this.eventAttendee;
    }
}
