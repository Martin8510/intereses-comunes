package red.social.interesescomunes.eventattendee.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.eventattendee.domain.event.EventAttendeeCreatedEvent;
import red.social.interesescomunes.eventattendee.domain.event.EventAttendeeUpdatedEvent;

@Component
public class EventAttendeeEventListener {
    @EventListener
    public void handleEventAttendeeCreated(EventAttendeeCreatedEvent event) {
        System.out.println("Asistencia a evento creada: " +
                event.getEventAttendee().getEventId() +
                " - Miembro: " + event.getEventAttendee().getMemberId());
    }

    @EventListener
    public void handleEventAttendeeUpdated(EventAttendeeUpdatedEvent event) {
        System.out.println("Asistencia a evento actualizada: " +
                event.getEventAttendee().getId() +
                " - Estado: " + event.getEventAttendee().getStatus());
    }
}
