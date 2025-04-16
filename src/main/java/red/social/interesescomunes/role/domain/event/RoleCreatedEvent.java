package red.social.interesescomunes.role.domain.event;

import red.social.interesescomunes.role.domain.model.Role;


/**
 * Representa un evento de dominio que indica la creación exitosa de un nuevo rol.
 * Contiene el estado del rol en el momento de su creación mediante un DTO.
 */
public class RoleCreatedEvent {
    private final RoleEventDTO role;

    public RoleCreatedEvent(Role role){
        this.role = RoleEventDTO.create(role);
    }

    public RoleEventDTO getRole(){
        return this.role;
    }
}
