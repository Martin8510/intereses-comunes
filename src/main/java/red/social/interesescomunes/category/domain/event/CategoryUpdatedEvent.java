package red.social.interesescomunes.category.domain.event;

import red.social.interesescomunes.category.domain.model.Category;


/**
 * Representa un evento de dominio que indica que la categoria existente se ha actualizado correctamente.
 * Contiene el estado actualizado de la categoria mediante un DTO.
 */
public class CategoryUpdatedEvent {
    private final CategoryEventDTO category;

    public CategoryUpdatedEvent(Category category) {
        this.category = CategoryEventDTO.create(category);
    }

    public CategoryEventDTO getCategory() {
        return this.category;
    }
}
