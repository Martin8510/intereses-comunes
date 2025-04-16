package red.social.interesescomunes.member.infrastructure.output.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.administrator.domain.enums.AdministratorStatus;
import red.social.interesescomunes.user.infrastructure.output.persistence.mysql.entity.UserEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "miembros")
@Data //getters, setters, equals(), hashCode(), y toString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio")
    private LocalDateTime startDate;

    @Column(name = "fecha_fin")
    private LocalDateTime endDate;

    @Column(name = "estado")
    private AdministratorStatus status;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "usuario_id")
    private UserEntity user;
}
