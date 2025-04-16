package red.social.interesescomunes.category.infrastructure.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import red.social.interesescomunes.category.domain.event.CategoryCreatedEvent;
import red.social.interesescomunes.category.domain.event.CategoryDeletedEvent;
import red.social.interesescomunes.category.domain.event.CategoryUpdatedEvent;

@Component
/**
 * Componente  que captura los  eventos de dominio relacionados con las categorias.
 */
public class CategoryEventListener {
    @EventListener
    public void handleCategoryCreated(CategoryCreatedEvent event) {
        System.out.println("Categoría creada: " + event.getCategory().getName());
    }

    @EventListener
    public void handleCategoryUpdated(CategoryUpdatedEvent event) {
        System.out.println("Categoría actualizada: " + event.getCategory().getName());
    }

    @EventListener
    public void handleCategoryDeleted(CategoryDeletedEvent event) {
        System.out.println("Categoría eliminada: " + event.getCategory().getName());
    }
}
