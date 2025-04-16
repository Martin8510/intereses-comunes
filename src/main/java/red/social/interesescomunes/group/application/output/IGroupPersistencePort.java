package red.social.interesescomunes.group.application.output;

import red.social.interesescomunes.group.domain.model.Group;

import java.util.List;
import java.util.Optional;

public interface IGroupPersistencePort {
    List<Group> findAll();
    Optional<Group> findById(Long id);
    Group save(Group group);
    void delete(Long id);
}
