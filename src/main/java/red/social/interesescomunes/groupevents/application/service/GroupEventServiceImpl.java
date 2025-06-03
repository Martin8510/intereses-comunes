package red.social.interesescomunes.groupevents.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import red.social.interesescomunes.group.application.input.IGroupServicePort;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.groupevents.application.input.IGroupEventServicePort;
import red.social.interesescomunes.groupevents.application.output.IGroupEventPersistencePort;
import red.social.interesescomunes.groupevents.domain.enums.GroupEventStatus;
import red.social.interesescomunes.groupevents.domain.event.IGroupEventDomainEventPublisher;
import red.social.interesescomunes.groupevents.domain.exception.GroupEventNotFoundException;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GroupEventServiceImpl implements IGroupEventServicePort {
    private final IGroupEventPersistencePort repository;
    private final IGroupServicePort groupService;
    private final IGroupEventDomainEventPublisher eventPublisher;

    public GroupEventServiceImpl(IGroupEventPersistencePort repository,
                                 IGroupServicePort groupService,
                                 IGroupEventDomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.groupService = groupService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<GroupEvent> findAllEvents() {
        return repository.findAll();
    }

    @Override
    public GroupEvent findEventById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GroupEventNotFoundException("No se encontró el evento con ID: " + id));
    }

    @Override
    @Transactional
    public GroupEvent createEvent(Long groupId, GroupEvent event) {
        Group group = groupService.findGroupById(groupId);
        event.setGroup(group);
        event.setStatus(GroupEventStatus.ACTIVO);
        event.setCreatedAt(LocalDateTime.now());
        event.setUpdatedAt(LocalDateTime.now());

        GroupEvent savedEvent = repository.save(event);
        savedEvent.create(eventPublisher);
        return savedEvent;
    }

    @Override
    @Transactional
    public GroupEvent updateEvent(Long id, GroupEvent event) {
        GroupEvent existingEvent = findEventById(id);

        existingEvent.setTitle(event.getTitle());
        existingEvent.setDescription(event.getDescription());
        existingEvent.setEventDate(event.getEventDate());
        existingEvent.setEventTime(event.getEventTime());
        existingEvent.setMaxCapacity(event.getMaxCapacity());
        existingEvent.setImageUrl(event.getImageUrl());
        existingEvent.setUpdatedAt(LocalDateTime.now());

        GroupEvent updatedEvent = repository.save(existingEvent);
        updatedEvent.update(eventPublisher);
        return updatedEvent;
    }

    @Override
    @Transactional
    public void cancelEvent(Long id) {
        GroupEvent event = findEventById(id);
        event.cancel(eventPublisher);
        repository.save(event);
    }

    @Override
    public List<GroupEvent> findEventsByGroup(Long groupId) {
        return repository.findByGroupId(groupId);
    }
}
