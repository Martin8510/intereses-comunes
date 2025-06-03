package red.social.interesescomunes.eventattendee.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.eventattendee.domain.enums.AttendanceStatus;
import red.social.interesescomunes.eventattendee.domain.event.IEventAttendeeDomainEventPublisher;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;
import red.social.interesescomunes.member.domain.model.Member;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventAttendee {
    private Long id;
    private Member member;
    private GroupEvent event;
    private AttendanceStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void create(IEventAttendeeDomainEventPublisher eventPublisher){
        eventPublisher.publishEventAttendeeCreated(this);
    }

    public void update(IEventAttendeeDomainEventPublisher eventPublisher){
        eventPublisher.publishEventAttendeeUpdated(this);
    }

}
