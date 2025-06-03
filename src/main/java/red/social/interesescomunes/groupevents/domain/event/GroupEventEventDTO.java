package red.social.interesescomunes.groupevents.domain.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupEventEventDTO {
    private Long id;
    private String title;
    private String description;
    private String eventDate;
    private String eventTime;
    private Integer maxCapacity;
    private String status;
    private Long groupId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GroupEventEventDTO create(GroupEvent event) {
        return GroupEventEventDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .eventDate(event.getEventDate().toString())
                .eventTime(event.getEventTime().toString())
                .maxCapacity(event.getMaxCapacity())
                .status(event.getStatus().name())
                .groupId(event.getGroup().getId())
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .build();
    }
}