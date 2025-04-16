package red.social.interesescomunes.group.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import red.social.interesescomunes.group.infrastructure.output.persistence.mysql.entity.GroupEntity;

public interface IGrouppaRepository extends CrudRepository<GroupEntity, Long> {
}