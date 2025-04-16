package red.social.interesescomunes.category.domain.event;

import red.social.interesescomunes.category.domain.model.Category;


/**
 * Representa un evento de dominio que indica que la categoria existente se ha eliminado correctamente.
 *  Contiene el estado de la categoria justo antes de su eliminación mediante un DTO.
 */
public class CategoryDeletedEvent {
    private final CategoryEventDTO category;

    public CategoryDeletedEvent(Category category) {
        this.category = CategoryEventDTO.create(category);
    }

    public CategoryEventDTO getCategory() {
        return this.category;
    }
}
