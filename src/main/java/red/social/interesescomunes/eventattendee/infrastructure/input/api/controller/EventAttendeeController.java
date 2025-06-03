package red.social.interesescomunes.eventattendee.infrastructure.input.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.eventattendee.application.input.IEventAttendeeServicePort;
import red.social.interesescomunes.eventattendee.domain.model.EventAttendee;
import red.social.interesescomunes.eventattendee.infrastructure.input.api.dto.request.EventAttendeeRequest;
import red.social.interesescomunes.eventattendee.infrastructure.input.api.dto.response.EventAttendeeResponse;
import red.social.interesescomunes.eventattendee.infrastructure.input.api.dto.response.UpdateAttendanceStatusRequest;
import red.social.interesescomunes.eventattendee.infrastructure.input.api.mapper.IEventAttendeeRestMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event-attendees")
public class EventAttendeeController {
    private final IEventAttendeeServicePort service;
    private final IEventAttendeeRestMapper mapper;

    public EventAttendeeController(IEventAttendeeServicePort service,
                                   IEventAttendeeRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<EventAttendeeResponse> createEventAttendee(
            @Valid @RequestBody EventAttendeeRequest request) {
        EventAttendee eventAttendee = mapper.toDomain(request);
        EventAttendee createdAttendee = service.createEventAttendee(eventAttendee);
        EventAttendeeResponse response = mapper.toResponse(createdAttendee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<EventAttendeeResponse>> findAllByEventId(
            @PathVariable Long eventId) {
        List<EventAttendee> attendees = service.findAllByEventId(eventId);
        List<EventAttendeeResponse> responses = mapper.toResponseList(attendees);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<EventAttendeeResponse>> findAllByMemberId(
            @PathVariable Long memberId) {
        List<EventAttendee> attendees = service.findAllByMemberId(memberId);
        List<EventAttendeeResponse> responses = mapper.toResponseList(attendees);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventAttendeeResponse> findById(@PathVariable Long id) {
        EventAttendee attendee = service.findById(id);
        EventAttendeeResponse response = mapper.toResponse(attendee);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EventAttendeeResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAttendanceStatusRequest request) {
        EventAttendee updatedAttendee = service.updateAttendanceStatus(id, request.getStatus().name());
        EventAttendeeResponse response = mapper.toResponse(updatedAttendee);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventAttendee(@PathVariable Long id) {
        service.deleteEventAttendee(id);
        return ResponseEntity.noContent().build();
    }
}