package red.social.interesescomunes.group.domain.event;

import red.social.interesescomunes.group.domain.model.Group;

public class GroupDeletedEvent {
    private final GroupEventDTO group;

    public GroupDeletedEvent(Group group) {
        this.group = GroupEventDTO.create(group);
    }

    public GroupEventDTO getGroup() {
        return this.group;
    }
}
