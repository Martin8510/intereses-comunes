package red.social.interesescomunes.category.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import red.social.interesescomunes.category.domain.event.CategoryCreatedEvent;
import red.social.interesescomunes.category.domain.event.CategoryDeletedEvent;
import red.social.interesescomunes.category.domain.event.CategoryUpdatedEvent;
import red.social.interesescomunes.category.domain.event.ICategoryDomainEventPublisher;
import red.social.interesescomunes.owner.domain.event.IOwnerDomainEventPublisher;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * Representa el modelo de dominio principal para una categoria.
 */
public class Category {
    private Long id;
    private String name;
    private String description;

    // evento de crear un rol
    public void create(ICategoryDomainEventPublisher eventPublisher){
        eventPublisher.publishCategoryCreated(this);
    }

    public void update(ICategoryDomainEventPublisher eventPublisher){
        eventPublisher.publishCategoryUpdated(this);
    }

    public void delete(ICategoryDomainEventPublisher eventPublisher){
        eventPublisher.publishCategoryDeleted(this);
    }
}
