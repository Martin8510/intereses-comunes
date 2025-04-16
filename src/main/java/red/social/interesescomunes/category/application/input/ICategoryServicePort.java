package red.social.interesescomunes.category.application.input;

import red.social.interesescomunes.category.domain.model.Category;
import java.util.List;

/**
 * Interfaz que define los metodos para casos de uso relacionados con categoria.
 */
public interface ICategoryServicePort {
    List<Category> findAllCategories();
    Category findCategoryById(Long id);
    Category findCategoryByName(String name);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategoryById(Long id);
}
