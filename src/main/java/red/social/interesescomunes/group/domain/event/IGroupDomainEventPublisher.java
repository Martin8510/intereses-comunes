package red.social.interesescomunes.group.domain.event;

import red.social.interesescomunes.group.domain.model.Group;

public interface IGroupDomainEventPublisher {
    void publishGroupCreated(Group group);
    void publishGroupUpdated(Group group);
    void publishGroupDeleted(Group group);
}
