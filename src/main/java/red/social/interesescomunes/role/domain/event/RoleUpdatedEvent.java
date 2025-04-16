package red.social.interesescomunes.role.domain.event;

import red.social.interesescomunes.role.domain.model.Role;

/**
 * Representa un evento de dominio que indica que un rol existente se ha actualizado correctamente.
 * Contiene el estado actualizado del rol mediante un DTO.
 */
public class RoleUpdatedEvent {
    private final RoleEventDTO role;

    public RoleUpdatedEvent(Role role){
        this.role = RoleEventDTO.create(role);
    }

    public RoleEventDTO getRole(){
        return this.role;
    }
}
