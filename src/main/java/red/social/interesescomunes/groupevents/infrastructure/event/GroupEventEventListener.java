package red.social.interesescomunes.groupevents.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.groupevents.domain.event.GroupEventCancelledEvent;
import red.social.interesescomunes.groupevents.domain.event.GroupEventCreatedEvent;
import red.social.interesescomunes.groupevents.domain.event.GroupEventUpdatedEvent;

@Component
public class GroupEventEventListener {
    @EventListener
    public void handleGroupEventCreated(GroupEventCreatedEvent event) {
        System.out.println("Evento creado: " + event.getEvent().getTitle());
    }

    @EventListener
    public void handleGroupEventUpdated(GroupEventUpdatedEvent event) {
        System.out.println("Evento actualizado: " + event.getEvent().getTitle());
    }

    @EventListener
    public void handleGroupEventCancelled(GroupEventCancelledEvent event) {
        System.out.println("Evento cancelado: " + event.getEvent().getTitle());
    }
}
