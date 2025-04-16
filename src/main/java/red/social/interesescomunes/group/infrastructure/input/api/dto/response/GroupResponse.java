package red.social.interesescomunes.group.infrastructure.input.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.category.infrastructure.input.api.dto.response.CategoryResponse;
import red.social.interesescomunes.group.domain.enums.GroupStatus;
import red.social.interesescomunes.owner.infrastructure.input.api.dto.response.OwnerResponse;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String imageUrl;
    private CategoryResponse category;
    private OwnerResponse owner;
    private GroupStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}