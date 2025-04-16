package red.social.interesescomunes.category.domain.event;

import red.social.interesescomunes.category.domain.model.Category;


/**
 * Representa un evento de dominio que indica la creación exitosa de un nueva categoria.
 * Contiene el estado de la categoria en el momento de su creación mediante un DTO.
 */
public class CategoryCreatedEvent {
    private final CategoryEventDTO category;

    public CategoryCreatedEvent(Category category) {
        this.category = CategoryEventDTO.create(category);
    }

    public CategoryEventDTO getCategory() {
        return this.category;
    }
}
