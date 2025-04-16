package red.social.interesescomunes.owner.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import red.social.interesescomunes.owner.infrastructure.output.persistence.mysql.entity.OwnerEntity;

import java.util.Optional;

public interface IOwnerJpaRepository extends CrudRepository<OwnerEntity,Long> {
    Optional<OwnerEntity> findByUserId(Long userId);
}
