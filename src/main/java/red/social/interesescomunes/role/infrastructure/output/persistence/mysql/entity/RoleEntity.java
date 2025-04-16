package red.social.interesescomunes.role.infrastructure.output.persistence.mysql.entity;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.role.domain.enums.TypeRole;
import red.social.interesescomunes.role.infrastructure.output.persistence.PermissionSetConverter;
import red.social.interesescomunes.security.model.Permission;

import java.util.Set;

/**
 * Entidad JPA que representa la tabla "Roles" en la base de datos.
 */
@Entity
@Table(name = "Roles")
@Data //getters, setters, equals(), hashCode(), y toString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "nombre")
    private TypeRole name;

    @Convert(converter = PermissionSetConverter.class)
    @Column(name = "permisos", columnDefinition = "JSON")
    private Set<Permission> permits;

    @Column(name = "descripcion")
    private String description;
}

