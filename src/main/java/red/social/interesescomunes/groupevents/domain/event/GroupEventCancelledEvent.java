package red.social.interesescomunes.groupevents.domain.event;

import red.social.interesescomunes.groupevents.domain.model.GroupEvent;

public class GroupEventCancelledEvent {
    private final GroupEventEventDTO event;

    public GroupEventCancelledEvent(GroupEvent event) {
        this.event = GroupEventEventDTO.create(event);
    }

    public GroupEventEventDTO getEvent() {
        return this.event;
    }
}
