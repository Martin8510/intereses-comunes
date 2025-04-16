package red.social.interesescomunes.role.application.input;

import red.social.interesescomunes.role.domain.model.Role;

import java.util.List;

/**
 * Interfaz de puerto que define los metodos para casos de uso relacionados con roles.
 */
public interface IRoleServicePort {
    List<Role> findAllRoles();
    Role findRoleById(Long id);
    Role findRoleByType(String name); // Nuevo método
    Role createRole(Role role);
    Role updateRole(Long id, Role role);
    void deleteRoleById(Long id);
}
