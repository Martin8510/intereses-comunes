package red.social.interesescomunes.role.domain.model;


import lombok.*;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.domain.event.IRoleDomainEventPublisher;
import red.social.interesescomunes.security.model.Permission;

import java.util.Set;

/**
 * Representa el modelo de dominio principal para un rol.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private Long id;
    private TypeRole name;
    private Set<Permission> permits;
    private String description;

    // evento de crear un rol
    public void create(IRoleDomainEventPublisher eventPublisher) {
        eventPublisher.publishRoleCreated(this);
    }

    // evento de actualizacion de un rol
    public void update(IRoleDomainEventPublisher eventPublisher) {
        eventPublisher.publishRoleUpdated(this);
    }

    // evento de eliminacion de un rol
    public void delete(IRoleDomainEventPublisher eventPublisher) {
        eventPublisher.publishRoleDeleted(this);
    }
}
