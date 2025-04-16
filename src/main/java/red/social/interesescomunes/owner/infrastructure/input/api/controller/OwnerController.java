package red.social.interesescomunes.owner.infrastructure.input.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.owner.application.input.IOwnerServicePort;
import red.social.interesescomunes.owner.domain.model.Owner;
import red.social.interesescomunes.owner.infrastructure.input.api.dto.request.OwnerRequest;
import red.social.interesescomunes.owner.infrastructure.input.api.dto.response.OwnerResponse;
import red.social.interesescomunes.owner.infrastructure.input.api.mapper.IOwnerRestMappert;
import red.social.interesescomunes.user.domain.exception.UserNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ower")
public class OwnerController  {
    private final IOwnerServicePort service;
    private final IOwnerRestMappert mappert;

    public OwnerController(IOwnerServicePort service, IOwnerRestMappert mappert) {
        this.service = service;
        this.mappert = mappert;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<OwnerResponse>> findAllOwners() {
        List<Owner> owners = this.service.findAllOwners();
        List<OwnerResponse> responses = this.mappert.toUserResponseList(owners);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<OwnerResponse> findOwnerById(@PathVariable Long id) {
        Owner owner = this.service.findOwnerById(id);
        OwnerResponse response = this.mappert.toUserResponse(owner);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/find-by-user-id/{id}")
    public ResponseEntity<OwnerResponse> findOwnerByUserId(@PathVariable Long id) {
        Owner owner = this.service.findOwnerByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontro un usuario con el id: " + id));

        OwnerResponse response = this.mappert.toUserResponse(owner);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OwnerResponse> updateOwner(@PathVariable Long id,@Valid @RequestBody OwnerRequest ownerRequest) {
        System.out.println("controller " + ownerRequest);
        Owner owner = this.mappert.toDomain(ownerRequest);
        Owner savedOwner = this.service.updateOwner(id,owner);
        OwnerResponse response = this.mappert.toUserResponse(savedOwner);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOwnerById(@PathVariable Long id) {
        this.service.deleteOwnerById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado.");
    }
}
