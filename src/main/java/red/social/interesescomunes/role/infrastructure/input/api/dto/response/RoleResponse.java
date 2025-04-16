package red.social.interesescomunes.role.infrastructure.input.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.security.model.Permission;

import java.util.Set;

/**
 * Objeto de Transferencia de Datos que representa los datos del rol enviados en las respuestas de la API REST.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private Long id;
    private TypeRole name;
    private Set<Permission> permits;
    private String description;
}
