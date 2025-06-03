package red.social.interesescomunes.administrator.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import red.social.interesescomunes.administrator.infrastructure.output.persistence.mysql.entity.AdministratorEntity;

import java.util.Optional;

public interface IAdministratorJpaRepository extends CrudRepository<AdministratorEntity,Long> {
    Optional<AdministratorEntity> findByUserId(Long userId);
}
