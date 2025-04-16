package red.social.interesescomunes.moderator.domain.event;

import red.social.interesescomunes.group.domain.model.Group;

public class ModeratorUpdatedEvent {
    private final Group group;

    public ModeratorUpdatedEvent(Group group){
        this.group = group;
    }

    public Group getGroup(){
        return this.group;
    }
}
