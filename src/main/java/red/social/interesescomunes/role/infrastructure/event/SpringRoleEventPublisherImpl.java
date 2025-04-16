package red.social.interesescomunes.role.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.role.domain.event.IRoleDomainEventPublisher;
import red.social.interesescomunes.role.domain.event.RoleCreatedEvent;
import red.social.interesescomunes.role.domain.event.RoleDeletedEvent;
import red.social.interesescomunes.role.domain.event.RoleUpdatedEvent;
import red.social.interesescomunes.role.domain.model.Role;


/**
 * Implementación concreta del publicador de eventos de dominio para la entidad Role.
 */
@Component
public class SpringRoleEventPublisherImpl  implements IRoleDomainEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public SpringRoleEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishRoleCreated(Role role) {
        eventPublisher.publishEvent(new RoleCreatedEvent(role));
    }

    @Override
    public void publishRoleUpdated(Role role) {
        eventPublisher.publishEvent(new RoleUpdatedEvent(role));
    }

    @Override
    public void publishRoleDeleted(Role role) {
        eventPublisher.publishEvent(new RoleDeletedEvent(role));
    }
}
