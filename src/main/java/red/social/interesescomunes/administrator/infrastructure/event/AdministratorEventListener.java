package red.social.interesescomunes.administrator.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.administrator.domain.event.AdministratorCreatedEvent;
import red.social.interesescomunes.administrator.domain.event.AdministratorDeletedEvent;
import red.social.interesescomunes.administrator.domain.event.AdministratorUpdatedEvent;

@Component
public class AdministratorEventListener {
    @EventListener
    public void handleAdministratorCreated(AdministratorCreatedEvent event) {
        System.out.println("Administrador creado: " + event.getAdministrator().getUser().getFirstName() + " " + event.getAdministrator().getUser().getLastName());
    }

    @EventListener
    public void handleAdministratorUpdated(AdministratorUpdatedEvent event) {
        System.out.println("Administrador actualizado: " + event.getAdministrator().getUser().getFirstName() + " " + event.getAdministrator().getUser().getLastName());
    }

    @EventListener
    public void handleAdministratorDeleted(AdministratorDeletedEvent event) {
        System.out.println("Administrador eliminado: " + event.getAdministrator().getUser().getFirstName() + " " + event.getAdministrator().getUser().getLastName());
    }
}
