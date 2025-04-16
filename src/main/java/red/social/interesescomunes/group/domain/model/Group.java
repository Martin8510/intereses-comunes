package red.social.interesescomunes.group.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import red.social.interesescomunes.category.domain.model.Category;
import red.social.interesescomunes.group.domain.enums.GroupStatus;
import red.social.interesescomunes.group.domain.event.IGroupDomainEventPublisher;
import red.social.interesescomunes.owner.domain.model.Owner;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    private Long id;
    private String name;
    private String description;
    private String location;
    private String imageUrl;
    private Category category;
    private Owner owner;
    private GroupStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // evento de crear un rol
    public void create(IGroupDomainEventPublisher eventPublisher) {
        eventPublisher.publishGroupCreated(this);
    }

    public void update(IGroupDomainEventPublisher eventPublisher) {
        eventPublisher.publishGroupUpdated(this);
    }

    public void delete(IGroupDomainEventPublisher eventPublisher) {
        eventPublisher.publishGroupDeleted(this);
    }
}
