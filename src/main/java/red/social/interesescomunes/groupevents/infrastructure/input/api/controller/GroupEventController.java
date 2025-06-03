package red.social.interesescomunes.groupevents.infrastructure.input.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.groupevents.application.input.IGroupEventServicePort;
import red.social.interesescomunes.groupevents.domain.model.GroupEvent;
import red.social.interesescomunes.groupevents.infrastructure.input.api.dto.request.GroupEventRequest;
import red.social.interesescomunes.groupevents.infrastructure.input.api.dto.response.GroupEventResponse;
import red.social.interesescomunes.groupevents.infrastructure.input.api.mapper.IGroupEventRestMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group-events")
public class GroupEventController {
    private final IGroupEventServicePort service;
    private final IGroupEventRestMapper mapper;

    public GroupEventController(IGroupEventServicePort service, IGroupEventRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupEventResponse>> findEventsByGroup(@PathVariable Long groupId) {
        List<GroupEvent> events = service.findEventsByGroup(groupId);
        List<GroupEventResponse> responses = mapper.toResponseList(events);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupEventResponse> findEventById(@PathVariable Long id) {
        GroupEvent event = service.findEventById(id);
        GroupEventResponse response = mapper.toResponse(event);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<GroupEventResponse> createEvent(
            @PathVariable Long id,
            @Valid @RequestBody GroupEventRequest request) {
        GroupEvent event = mapper.toDomain(request);
        GroupEvent createdEvent = service.createEvent(id, event);
        GroupEventResponse response = mapper.toResponse(createdEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GroupEventResponse> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody GroupEventRequest request) {
        GroupEvent event = mapper.toDomain(request);
        GroupEvent updatedEvent = service.updateEvent(id, event);
        GroupEventResponse response = mapper.toResponse(updatedEvent);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelEvent(@PathVariable Long id) {
        service.cancelEvent(id);
        return ResponseEntity.noContent().build();
    }
}
