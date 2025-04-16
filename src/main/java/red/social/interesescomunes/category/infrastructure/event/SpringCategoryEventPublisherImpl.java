package red.social.interesescomunes.category.infrastructure.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.category.domain.event.CategoryCreatedEvent;
import red.social.interesescomunes.category.domain.event.CategoryDeletedEvent;
import red.social.interesescomunes.category.domain.event.CategoryUpdatedEvent;
import red.social.interesescomunes.category.domain.event.ICategoryDomainEventPublisher;
import red.social.interesescomunes.category.domain.model.Category;

@Component
/**
 * Componente que se encarga de publicar los eventos de dominio para la entidad categoria.
 */
public class SpringCategoryEventPublisherImpl implements ICategoryDomainEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public SpringCategoryEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publishCategoryCreated(Category category) {
        eventPublisher.publishEvent(new CategoryCreatedEvent(category));
    }

    @Override
    public void publishCategoryUpdated(Category category) {
        eventPublisher.publishEvent(new CategoryUpdatedEvent(category));
    }

    @Override
    public void publishCategoryDeleted(Category category) {
        eventPublisher.publishEvent(new CategoryDeletedEvent(category));
    }
}
