package red.social.interesescomunes.eventattendee.infrastructure.output.persistence.mysql.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.eventattendee.domain.enums.AttendanceStatus;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;
import red.social.interesescomunes.groupevents.infrastructure.output.persistence.mysql.entity.GroupEventEntity;
import red.social.interesescomunes.member.infrastructure.output.persistence.mysql.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "asistentes_evento")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventAttendeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "miembro_id", nullable = false)
    private MemberEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    private GroupEventEntity event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime updatedAt;
}
