package red.social.interesescomunes.owner.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.owner.domain.event.OwnerCreatedEvent;
import red.social.interesescomunes.owner.domain.event.OwnerDeletedEvent;
import red.social.interesescomunes.owner.domain.event.OwnerUpdatedEvent;

@Component
public class OwnerEventListener {
    @EventListener
    public void handleOwnerCreated(OwnerCreatedEvent event){
        System.out.println("Usuario creado: " + event.getOwner().getUser().getFirstName() + " " + event.getOwner().getUser().getLastName());
    }

    @EventListener
    public void handleOwnerUpdated(OwnerUpdatedEvent event){
        System.out.println("Usuario actualizado: " + event.getOwner().getUser().getFirstName() + " " + event.getOwner().getUser().getLastName());
    }

    @EventListener
    public void handleOwnerDeleted(OwnerDeletedEvent event) {
        System.out.println("Usuario eliminado: " + event.getOwner().getUser().getFirstName() + " " + event.getOwner().getUser().getLastName());
    }
}
