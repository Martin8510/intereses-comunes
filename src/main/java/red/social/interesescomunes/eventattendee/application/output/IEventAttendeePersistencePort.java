package red.social.interesescomunes.eventattendee.application.output;

import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;

import java.util.List;
import java.util.Optional;

public interface IEventAttendeePersistencePort {
    EventAttendee save(EventAttendee eventAttendee);
    Optional<EventAttendee> findById(Long id);
    List<EventAttendee> findAllByEventId(Long eventId);
    List<EventAttendee> findAllByMemberId(Long memberId);
    void delete(Long id);
    boolean existsByMemberAndEvent(Long memberId, Long eventId);
}