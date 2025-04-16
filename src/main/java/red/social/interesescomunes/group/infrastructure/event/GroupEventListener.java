package red.social.interesescomunes.group.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.group.domain.event.GroupCreatedEvent;
import red.social.interesescomunes.group.domain.event.GroupDeletedEvent;
import red.social.interesescomunes.group.domain.event.GroupUpdatedEvent;

@Component
public class GroupEventListener {
    @EventListener
    public void handleGroupCreated(GroupCreatedEvent event) {
        System.out.println("Grupo creado: " + event.getGroup().getName());
    }

    @EventListener
    public void handleGroupUpdated(GroupUpdatedEvent event) {
        System.out.println("Grupo actualizado: " + event.getGroup().getName());
    }

    @EventListener
    public void handleGroupDeleted(GroupDeletedEvent event) {
        System.out.println("Grupo eliminado: " + event.getGroup().getName());
    }
}
