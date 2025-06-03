package red.social.interesescomunes.category.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.category.application.input.ICategoryServicePort;
import red.social.interesescomunes.category.application.output.ICategoryPersistencePort;
import red.social.interesescomunes.category.domain.event.ICategoryDomainEventPublisher;
import red.social.interesescomunes.category.domain.exception.CategoryNameAlreadyExistsException;
import red.social.interesescomunes.category.domain.exception.CategoryNotFoundException;
import red.social.interesescomunes.category.domain.model.Category;
import java.util.List;
import java.util.Optional;


/**
 * Servicio de aplicación que implementa los casos de uso relacionados con las categorias.
 */
@Service
public class CategoryServiceImpl implements ICategoryServicePort {
    private final ICategoryPersistencePort repository;
    private final ICategoryDomainEventPublisher eventPublisher;

    public CategoryServiceImpl(ICategoryPersistencePort repository, ICategoryDomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Category> findAllCategories() {
        return this.repository.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No se encontró una categoría con el ID " + id));
    }

    @Override
    public Category findCategoryByName(String name) {
        return this.repository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("No se encontró una categoría con el nombre " + name));
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {

        Optional<Category> categoryNameOptional =  this.repository.findByName(category.getName());

        if(categoryNameOptional.isPresent()){
            throw new CategoryNameAlreadyExistsException(
                    "Ya existe una categoría con el nombre: " + category.getName()
            );
        }

        Category categoryCreated = this.repository.save(category);
        categoryCreated.create(this.eventPublisher);
        return categoryCreated;
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, Category category) {
        Category existingCategory = this.repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No se encontró una categoría con el ID " + id));

        // Actualizamos los datos
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());

        // Guardamos y publicamos el evento
        Category categoryUpdated = this.repository.save(existingCategory);
        categoryUpdated.update(this.eventPublisher);
        return categoryUpdated;
    }

    @Override
    public void deleteCategoryById(Long id) {
        Category existingCategory = this.repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("No se encontró una categoría con el ID " + id));

        // Eliminamos y publicamos el evento
        this.repository.delete(existingCategory.getId());
        existingCategory.delete(this.eventPublisher);
    }
}