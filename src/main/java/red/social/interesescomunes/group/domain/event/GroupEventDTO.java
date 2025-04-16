package red.social.interesescomunes.group.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.group.domain.model.Group;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupEventDTO {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String imageUrl;
    private Long categoryId;
    private Long ownerId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GroupEventDTO create(Group group) {
        return GroupEventDTO.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .location(group.getLocation())
                .imageUrl(group.getImageUrl())
                .categoryId(group.getCategory().getId())
                .ownerId(group.getOwner().getId())
                .status(group.getStatus().name())
                .createdAt(group.getCreatedAt())
                .updatedAt(group.getUpdatedAt())
                .build();
    }
}