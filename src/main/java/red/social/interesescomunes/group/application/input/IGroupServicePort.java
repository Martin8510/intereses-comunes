package red.social.interesescomunes.group.application.input;

import red.social.interesescomunes.group.domain.model.Group;

import java.util.List;

public interface IGroupServicePort {
    List<Group> findAllGroups();
    Group findGroupById(Long id);
    Group createGroup(Long id,Group group);
    Group updateGroup(Long id, Group group);
    void deleteGroupById(Long id);
}
