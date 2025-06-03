package red.social.interesescomunes.eventattendee.infrastructure.output.persistence.mysql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import red.social.interesescomunes.eventattendee.infrastructure.output.persistence.mysql.entity.EventAttendeeEntity;

import java.util.List;

@Repository
public interface IEventAttendeeJpaRepository extends CrudRepository<EventAttendeeEntity, Long> {
    List<EventAttendeeEntity> findAllByEventId(Long eventId);
    List<EventAttendeeEntity> findAllByMemberId(Long memberId);
    boolean existsByMemberIdAndEventId(Long memberId, Long eventId);
}