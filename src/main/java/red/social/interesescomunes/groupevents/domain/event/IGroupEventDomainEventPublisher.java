package red.social.interesescomunes.groupevents.domain.event;

import red.social.interesescomunes.groupevents.domain.model.GroupEvent;

public interface IGroupEventDomainEventPublisher {
    void publishGroupEventCreated(GroupEvent event);
    void publishGroupEventUpdated(GroupEvent event);
    void publishGroupEventCancelled(GroupEvent event);
}