package red.social.interesescomunes.group.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import red.social.interesescomunes.group.infrastructure.output.persistence.mysql.entity.GroupEntity;

@Repository
public interface IGrouppaRepository extends CrudRepository<GroupEntity, Long> {
    boolean existsByName(String name);
}