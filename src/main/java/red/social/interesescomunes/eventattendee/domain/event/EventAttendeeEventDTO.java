package red.social.interesescomunes.eventattendee.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventAttendeeEventDTO {
    private Long id;
    private Long memberId;
    private Long eventId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static EventAttendeeEventDTO create(EventAttendee eventAttendee) {
        return EventAttendeeEventDTO.builder()
                .id(eventAttendee.getId())
                .memberId(eventAttendee.getMember().getId())
                .eventId(eventAttendee.getEvent().getId())
                .status(eventAttendee.getStatus().name())
                .createdAt(eventAttendee.getCreatedAt())
                .updatedAt(eventAttendee.getUpdatedAt())
                .build();
    }
}
