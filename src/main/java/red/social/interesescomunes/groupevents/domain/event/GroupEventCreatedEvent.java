package red.social.interesescomunes.groupevents.domain.event;

import red.social.interesescomunes.groupevents.domain.model.GroupEvent;

public class GroupEventCreatedEvent {
    private final GroupEventEventDTO event;

    public GroupEventCreatedEvent(GroupEvent event) {
        this.event = GroupEventEventDTO.create(event);
    }

    public GroupEventEventDTO getEvent() {
        return this.event;
    }
}
