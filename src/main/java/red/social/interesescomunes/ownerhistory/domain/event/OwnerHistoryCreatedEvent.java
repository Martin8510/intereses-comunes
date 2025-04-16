package red.social.interesescomunes.ownerhistory.domain.event;

import red.social.interesescomunes.group.domain.model.Group;

public class OwnerHistoryCreatedEvent {
    private final Group group;

    public OwnerHistoryCreatedEvent(Group group){
        this.group = group;
    }

    public Group getGroup(){
        return this.group;
    }
}
