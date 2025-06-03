package red.social.interesescomunes.groupevents.application.output;

import red.social.interesescomunes.groupevents.domain.model.GroupEvent;

import java.util.List;
import java.util.Optional;

public interface IGroupEventPersistencePort {
    List<GroupEvent> findAll();
    Optional<GroupEvent> findById(Long id);
    GroupEvent save(GroupEvent event);
    List<GroupEvent> findByGroupId(Long groupId);
}