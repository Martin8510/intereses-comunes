package red.social.interesescomunes.category.domain.event;

import red.social.interesescomunes.category.domain.model.Category;


/**
 * Interfaz que define los metodos para la publicación de eventos de dominio relacionados con las categorias.
 */
public interface ICategoryDomainEventPublisher {
    /**
     * Publica un evento que indica que se ha creado un nueva categoria.
     * @param category La categoria creado.
     */
    void publishCategoryCreated(Category category);

    /**
     * Publica un evento que indica que se ha actualizado la categoria.
     * @param category La categoria actualizado.
     */
    void publishCategoryUpdated(Category category);

    /**
     * Publica un evento que indica que se ha eliminado la categoria.
     * @param category La categoria es eliminado.
     */
    void publishCategoryDeleted(Category category);
}
