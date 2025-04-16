package red.social.interesescomunes.group.infrastructure.output.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.category.infrastructure.output.persistence.mysql.entity.CategoryEntity;
import red.social.interesescomunes.group.domain.enums.GroupStatus;
import red.social.interesescomunes.owner.infrastructure.output.persistence.mysql.entity.OwnerEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "grupos_tematicos")
@Data //getters, setters, equals(), hashCode(), y toString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "ubicacion")
    private String location;

    @Column(name = "url_imagen")
    private String imageUrl;

    @ManyToOne(
        cascade = CascadeType.MERGE,
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "categoria_id")
    private CategoryEntity category;

    @ManyToOne(
        cascade = CascadeType.MERGE,
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "propietario_id", nullable = false)
    private OwnerEntity owner;

    @Column(name = "estado")
    private GroupStatus status;

    @Column(name = "creado_en")
    private LocalDateTime createdAt;

    @Column(name = "actualizado_en")
    private LocalDateTime updatedAt;
}

