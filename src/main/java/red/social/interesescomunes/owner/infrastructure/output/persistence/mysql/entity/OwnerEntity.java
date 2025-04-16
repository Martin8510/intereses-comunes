package red.social.interesescomunes.owner.infrastructure.output.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.owner.domain.enums.OwnerStatus;
import red.social.interesescomunes.user.infrastructure.output.persistence.mysql.entity.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "propietarios")
@Data //getters, setters, equals(), hashCode(), y toString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio")
    private LocalDateTime startDate;

    @Column(name = "fecha_fin")
    private LocalDateTime endDate;

    @Column(name = "estado")
    private OwnerStatus status;
    //(relación muchos propietarios a un usuario)
    @ManyToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "usuario_id")
    private UserEntity user;
}
