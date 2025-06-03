package red.social.interesescomunes.eventattendee.infrastructure.output.persistence;

import org.springframework.stereotype.Component;
import red.social.interesescomunes.eventattendee.application.output.IEventAttendeePersistencePort;
import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;
import red.social.interesescomunes.eventattendee.infrastructure.output.persistence.mapper.IEventAttendeePersistenceMapper;
import red.social.interesescomunes.eventattendee.infrastructure.output.persistence.mysql.entity.EventAttendeeEntity;
import red.social.interesescomunes.eventattendee.infrastructure.output.persistence.mysql.repository.IEventAttendeeJpaRepository;

import java.util.List;
import java.util.Optional;

@Component
public class EventAttendeePersistenceAdapter implements IEventAttendeePersistencePort {
    private final IEventAttendeeJpaRepository jpaRepository;
    private final IEventAttendeePersistenceMapper mapper;

    public EventAttendeePersistenceAdapter(IEventAttendeeJpaRepository jpaRepository,
                                           IEventAttendeePersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public EventAttendee save(EventAttendee eventAttendee) {
        EventAttendeeEntity entity = mapper.toEntity(eventAttendee);
        EventAttendeeEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<EventAttendee> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<EventAttendee> findAllByEventId(Long eventId) {
        List<EventAttendeeEntity> entities = jpaRepository.findAllByEventId(eventId);
        return mapper.toDomainList(entities);
    }

    @Override
    public List<EventAttendee> findAllByMemberId(Long memberId) {
        List<EventAttendeeEntity> entities = jpaRepository.findAllByMemberId(memberId);
        return mapper.toDomainList(entities);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByMemberAndEvent(Long memberId, Long eventId) {
        return jpaRepository.existsByMemberIdAndEventId(memberId, eventId);
    }
}