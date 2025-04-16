package red.social.interesescomunes.role.domain.event;

import red.social.interesescomunes.role.domain.model.Role;

/**
 * Representa un evento de dominio que indica que un rol se ha eliminado correctamente.
 * Contiene el estado del rol justo antes de su eliminación mediante un DTO.
 */
public class RoleDeletedEvent {
    private final RoleEventDTO role;

    public RoleDeletedEvent(Role role){
        this.role = RoleEventDTO.create(role);
    }

    public RoleEventDTO getRole(){
        return this.role;
    }
}
