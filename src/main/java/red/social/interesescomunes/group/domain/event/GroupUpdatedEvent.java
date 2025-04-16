package red.social.interesescomunes.group.domain.event;

import red.social.interesescomunes.group.domain.model.Group;

public class GroupUpdatedEvent {
    private final GroupEventDTO group;

    public GroupUpdatedEvent(Group group) {
        this.group = GroupEventDTO.create(group);
    }

    public GroupEventDTO getGroup() {
        return this.group;
    }
}
