package red.social.interesescomunes.category.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import red.social.interesescomunes.category.infrastructure.output.persistence.mysql.entity.CategoryEntity;
import java.util.Optional;


/**
 *  Interfaz que proporciona operaciones CRUD estándar y permite definir métodos de consulta personalizados.
 * Para interactuar con la tabla Category.
 */
@Repository
public interface ICategoryJpaRepository extends CrudRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}