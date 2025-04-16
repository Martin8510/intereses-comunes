package red.social.interesescomunes.role.infrastructure.input.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.security.model.Permission;

import java.util.Set;

/**
 * Objeto de Transferencia de Datos (DTO) que representa la carga útil de la peticion para crear o actualizar un Rol
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest {
    @NotNull(message = "El nombre del rol es requerido.")
    private TypeRole name;
    @NotNull(message = "Los permisos son requeridos.")
    private Set<Permission> permits;
    @NotNull(message = "La descripcion del rol es requerido.")
    private String description;
}
