package red.social.interesescomunes.category.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.category.application.output.ICategoryPersistencePort;
import red.social.interesescomunes.category.domain.model.Category;
import red.social.interesescomunes.category.infrastructure.output.persistence.mapper.ICategoryPersistenceMapper;
import red.social.interesescomunes.category.infrastructure.output.persistence.mysql.entity.CategoryEntity;
import red.social.interesescomunes.category.infrastructure.output.persistence.mysql.repository.ICategoryJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


/**
 *
 * Componente de persistencia para realizar operaciones con datos relacionados con la tabla category.
 */
@Component
public class CategoryPersistenceAdapter implements ICategoryPersistencePort {
    private final ICategoryJpaRepository jpaRepository;
    private final ICategoryPersistenceMapper mapper;
    @PersistenceContext
    private EntityManager entityManager;

    public CategoryPersistenceAdapter(ICategoryJpaRepository jpaRepository, ICategoryPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Category> findAll() {
        List<CategoryEntity> categoryEntities = (List<CategoryEntity>) this.jpaRepository.findAll();
        return this.mapper.toDomainList(categoryEntities);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Category> findByName(String name) {
        entityManager.flush();
        entityManager.clear();
        return this.jpaRepository.findByName(name)
                .map(mapper::toDomain);
    }

    @Override
    public Category save(Category category) {
        entityManager.flush();
        entityManager.clear();
        CategoryEntity categoryEntity = this.mapper.toEntity(category);
        CategoryEntity savedCategory = this.jpaRepository.save(categoryEntity);
        return this.mapper.toDomain(savedCategory);
    }

    @Override
    public void delete(Long id) {
        this.jpaRepository.deleteById(id);
    }
}