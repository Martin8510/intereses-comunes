package red.social.interesescomunes.eventattendee.domain.event;


import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;

public class EventAttendeeUpdatedEvent {
    private final EventAttendeeEventDTO eventAttendee;

    public EventAttendeeUpdatedEvent(EventAttendee eventAttendee) {
        this.eventAttendee = EventAttendeeEventDTO.create(eventAttendee);
    }

    public EventAttendeeEventDTO getEventAttendee() {
        return this.eventAttendee;
    }
}
