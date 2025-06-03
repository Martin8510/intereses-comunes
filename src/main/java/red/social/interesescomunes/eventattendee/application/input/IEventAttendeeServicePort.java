package red.social.interesescomunes.eventattendee.application.input;

import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;

import java.util.List;

public interface IEventAttendeeServicePort {
    EventAttendee createEventAttendee(EventAttendee eventAttendee);
    EventAttendee updateEventAttendee(Long id, EventAttendee eventAttendee);
    List<EventAttendee> findAllByEventId(Long eventId);
    List<EventAttendee> findAllByMemberId(Long memberId);
    EventAttendee findById(Long id);
    void deleteEventAttendee(Long id);
    EventAttendee updateAttendanceStatus(Long id, String status);
}