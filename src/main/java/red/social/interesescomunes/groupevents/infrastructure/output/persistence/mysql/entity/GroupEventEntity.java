package red.social.interesescomunes.groupevents.infrastructure.output.persistence.mysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.group.infrastructure.output.persistence.mysql.entity.GroupEntity;
import red.social.interesescomunes.groupevents.domain.enums.GroupEventStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "eventos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDate eventDate;

    @Column(name = "hora_evento", nullable = false)
    private LocalTime eventTime;

    @Column(name = "capacidad_maxima")
    private Integer maxCapacity;

    @Column(name = "url_imagen")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupEventStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupo_id", nullable = false)
    private GroupEntity group;

    @Column(name = "creado_en")
    private LocalDateTime createdAt;

    @Column(name = "actualizado_en")
    private LocalDateTime updatedAt;
}