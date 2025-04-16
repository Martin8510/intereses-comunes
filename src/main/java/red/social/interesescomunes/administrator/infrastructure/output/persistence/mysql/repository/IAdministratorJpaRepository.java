package red.social.interesescomunes.administrator.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import red.social.interesescomunes.administrator.infrastructure.output.persistence.mysql.entity.AdministratorEntity;

public interface IAdministratorJpaRepository extends CrudRepository<AdministratorEntity,Long> {
}
