package red.social.interesescomunes.category.infrastructure.output.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categorias")
@Data //getters, setters, equals(), hashCode(), y toString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Entidad JPA que representa la tabla "Category" en la base de datos.
 */
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "descripcion")
    private String description;
}
