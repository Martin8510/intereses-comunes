package red.social.interesescomunes.group.domain.event;

import red.social.interesescomunes.group.domain.model.Group;

public class GroupCreatedEvent {
    private final GroupEventDTO group;

    public GroupCreatedEvent(Group group) {
        this.group = GroupEventDTO.create(group);
    }

    public GroupEventDTO getGroup() {
        return this.group;
    }
}
