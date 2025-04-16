package red.social.interesescomunes.role.domain.event;

import red.social.interesescomunes.role.domain.model.Role;

/**
 * Interfaz de puerto de salida que define los metodos para la publicación de eventos de dominio relacionados con los roles.
 */
public interface IRoleDomainEventPublisher {
    /**
     * Publica un evento que indica que se ha creado un nuevo rol.
     * @param role El rol creado.
     */

    void publishRoleCreated(Role role);
    /**
     * Publica un evento que indica que se ha actualizado un rol existente.
     * @param role El rol actualizado.
     */
    void publishRoleUpdated(Role role);
    /**
     * Publica un evento que indica que se ha eliminado un rol.
     * @param role El rol eliminado.
     */
    void publishRoleDeleted(Role role);
}
