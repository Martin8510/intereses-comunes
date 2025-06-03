package red.social.interesescomunes.groupevents.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.groupevents.domain.enums.GroupEventStatus;
import red.social.interesescomunes.groupevents.domain.event.IGroupEventDomainEventPublisher;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupEvent {
    private Long id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private Integer maxCapacity;
    private String imageUrl;
    private GroupEventStatus status;
    private Group group;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void create(IGroupEventDomainEventPublisher eventPublisher) {
        eventPublisher.publishGroupEventCreated(this);
    }

    public void update(IGroupEventDomainEventPublisher eventPublisher) {
        eventPublisher.publishGroupEventUpdated(this);
    }

    public void cancel(IGroupEventDomainEventPublisher eventPublisher) {
        this.status = GroupEventStatus.CANCELADO;
        eventPublisher.publishGroupEventCancelled(this);
    }
}
