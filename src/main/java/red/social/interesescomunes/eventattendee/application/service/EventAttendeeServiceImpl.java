package red.social.interesescomunes.eventattendee.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.eventattendee.application.input.IEventAttendeeServicePort;
import red.social.interesescomunes.eventattendee.application.output.IEventAttendeePersistencePort;
import red.social.interesescomunes.eventattendee.domain.enums.AttendanceStatus;
import red.social.interesescomunes.eventattendee.domain.event.IEventAttendeeDomainEventPublisher;
import red.social.interesescomunes.eventattendee.domain.exception.EventAttendeeAlreadyExistsException;
import red.social.interesescomunes.eventattendee.domain.exception.EventAttendeeNotFoundException;
import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventAttendeeServiceImpl implements IEventAttendeeServicePort {
    private final IEventAttendeePersistencePort repository;
    private final IEventAttendeeDomainEventPublisher eventPublisher;

    public EventAttendeeServiceImpl(IEventAttendeePersistencePort repository,
                                    IEventAttendeeDomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public EventAttendee createEventAttendee(EventAttendee eventAttendee) {
        if (repository.existsByMemberAndEvent(
                eventAttendee.getMember().getId(),
                eventAttendee.getEvent().getId())) {
            throw new EventAttendeeAlreadyExistsException(
                    "El miembro ya está registrado en este evento");
        }

        eventAttendee.setCreatedAt(LocalDateTime.now());
        eventAttendee.setUpdatedAt(LocalDateTime.now());
        eventAttendee.setStatus(AttendanceStatus.PENDIENTE);

        EventAttendee savedAttendee = repository.save(eventAttendee);
        eventPublisher.publishEventAttendeeCreated(savedAttendee);
        return savedAttendee;
    }

    @Override
    @Transactional
    public EventAttendee updateEventAttendee(Long id, EventAttendee eventAttendee) {
        EventAttendee existingAttendee = repository.findById(id)
                .orElseThrow(() -> new EventAttendeeNotFoundException(
                        "No se encontró asistencia al evento con ID " + id));

        existingAttendee.setStatus(eventAttendee.getStatus());
        existingAttendee.setUpdatedAt(LocalDateTime.now());

        EventAttendee updatedAttendee = repository.save(existingAttendee);
        eventPublisher.publishEventAttendeeUpdated(updatedAttendee);
        return updatedAttendee;
    }

    @Override
    @Transactional
    public EventAttendee updateAttendanceStatus(Long id, String status) {
        EventAttendee existingAttendee = repository.findById(id)
                .orElseThrow(() -> new EventAttendeeNotFoundException(
                        "No se encontró asistencia al evento con ID " + id));

        existingAttendee.setStatus(AttendanceStatus.valueOf(status.toUpperCase()));
        existingAttendee.setUpdatedAt(LocalDateTime.now());

        EventAttendee updatedAttendee = repository.save(existingAttendee);
        eventPublisher.publishEventAttendeeUpdated(updatedAttendee);
        return updatedAttendee;
    }

    @Override
    public List<EventAttendee> findAllByEventId(Long eventId) {
        return repository.findAllByEventId(eventId);
    }

    @Override
    public List<EventAttendee> findAllByMemberId(Long memberId) {
        return repository.findAllByMemberId(memberId);
    }

    @Override
    public EventAttendee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventAttendeeNotFoundException(
                        "No se encontró asistencia al evento con ID " + id));
    }

    @Override
    public void deleteEventAttendee(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new EventAttendeeNotFoundException(
                        "No se encontró asistencia al evento con ID " + id));
        repository.delete(id);
    }
}
