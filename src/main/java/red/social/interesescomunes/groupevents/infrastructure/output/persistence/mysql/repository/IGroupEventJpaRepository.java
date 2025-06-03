package red.social.interesescomunes.groupevents.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import red.social.interesescomunes.groupevents.infrastructure.output.persistence.mysql.entity.GroupEventEntity;

import java.util.List;

@Repository
public interface IGroupEventJpaRepository extends CrudRepository<GroupEventEntity, Long> {
    List<GroupEventEntity> findByGroupId(Long groupId);
}
