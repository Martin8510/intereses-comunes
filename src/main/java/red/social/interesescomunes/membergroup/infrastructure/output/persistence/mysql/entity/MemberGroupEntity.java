package red.social.interesescomunes.membergroup.infrastructure.output.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.*;
import red.social.interesescomunes.group.infrastructure.output.persistence.mysql.entity.GroupEntity;
import red.social.interesescomunes.member.domain.enums.MemberStatus;
import red.social.interesescomunes.member.infrastructure.output.persistence.mysql.entity.MemberEntity;

import java.time.LocalDate;

@Entity
@Table(name = "miembro_grupo_tematico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "miembro_id", nullable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "grupo_id", nullable = false)
    private GroupEntity group;

    @Column(name = "fecha_union")
    private LocalDate joinedAt;

    @Column(name = "fecha_salida")
    private LocalDate leftAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private MemberStatus status;
}
