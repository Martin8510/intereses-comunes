package red.social.interesescomunes.groupevents.infrastructure.input.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.groupevents.domain.enums.GroupEventStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupEventResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private Integer maxCapacity;
    private String imageUrl;
    private GroupEventStatus status;
    private Long groupId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
