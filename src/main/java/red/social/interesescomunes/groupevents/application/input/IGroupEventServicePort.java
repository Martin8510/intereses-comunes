package red.social.interesescomunes.groupevents.application.input;

import red.social.interesescomunes.groupevents.domain.model.GroupEvent;

import java.util.List;

public interface IGroupEventServicePort {
    List<GroupEvent> findAllEvents();
    GroupEvent findEventById(Long id);
    GroupEvent createEvent(Long groupId, GroupEvent event);
    GroupEvent updateEvent(Long id, GroupEvent event);
    void cancelEvent(Long id);
    List<GroupEvent> findEventsByGroup(Long groupId);
}
