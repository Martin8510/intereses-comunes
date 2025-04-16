package red.social.interesescomunes.group.infrastructure.input.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.group.application.input.IGroupServicePort;
import red.social.interesescomunes.group.domain.model.Group;
import red.social.interesescomunes.group.infrastructure.input.api.dto.request.GroupRequest;
import red.social.interesescomunes.group.infrastructure.input.api.dto.response.GroupResponse;
import red.social.interesescomunes.group.infrastructure.input.api.mapper.IGroupRestMapper;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
public class GroupController {
    private final IGroupServicePort service;
    private final IGroupRestMapper mapper;

    public GroupController(IGroupServicePort service, IGroupRestMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<GroupResponse>> findAllGroups() {
        List<Group> groups = this.service.findAllGroups();
        List<GroupResponse> responses = this.mapper.toResponseList(groups);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<GroupResponse> findGroupById(@PathVariable Long id) {
        Group group = this.service.findGroupById(id);
        GroupResponse response = this.mapper.toResponse(group);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<GroupResponse> createGroup(@PathVariable Long id,@Valid @RequestBody GroupRequest groupRequest) {
        System.out.println("contrador groupRequest :" + groupRequest);
        Group group = this.mapper.toDomain(groupRequest);
        System.out.println("contrador group   :" + group);
        Group savedGroup = this.service.createGroup(id,group);
        GroupResponse response = this.mapper.toResponse(savedGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GroupResponse> updateGroup(@PathVariable Long id, @Valid @RequestBody GroupRequest groupRequest) {
        Group group = this.mapper.toDomain(groupRequest);
        Group updatedGroup = this.service.updateGroup(id, group);
        GroupResponse response = this.mapper.toResponse(updatedGroup);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteGroupById(@PathVariable Long id) {
        this.service.deleteGroupById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Grupo eliminado.");
    }
}