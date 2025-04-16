package red.social.interesescomunes.administrator.infrastructure.input.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import red.social.interesescomunes.administrator.application.input.IAdministratorServicePort;
import red.social.interesescomunes.administrator.domain.model.Administrator;
import red.social.interesescomunes.administrator.infrastructure.input.api.dto.request.AdministratorRequest;
import red.social.interesescomunes.administrator.infrastructure.input.api.dto.response.AdministratorResponse;
import red.social.interesescomunes.administrator.infrastructure.input.api.mapper.IAdministratorRestMappert;
import red.social.interesescomunes.auth.application.input.IAuthServicePort;
import red.social.interesescomunes.auth.infrastructure.input.api.dto.response.AuthUserResponse;
import red.social.interesescomunes.user.infrastructure.input.api.dto.request.UserRequest;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdministratorController {
    private final IAdministratorServicePort service;
    private final IAdministratorRestMappert mapper;
    private final IAuthServicePort authService;

    public AdministratorController(IAdministratorServicePort service, IAdministratorRestMappert mapper, IAuthServicePort authService) {
        this.service = service;
        this.mapper = mapper;
        this.authService = authService;
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<AdministratorResponse>> findAllAdministrators() {
        List<Administrator> administrators = this.service.findAllAdministrators();
        List<AdministratorResponse> responses = this.mapper.toUserResponseList(administrators);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AdministratorResponse> findAdministratorById(@PathVariable Long id) {
        Administrator administrator = this.service.findAdministratorById(id);
        AdministratorResponse response = this.mapper.toUserResponse(administrator);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthUserResponse> createAdministrator(@Valid @RequestBody AdministratorRequest administratorRequest) {
        UserRequest userRequest = this.authService.encryptUserPassword(administratorRequest.getUser());
        administratorRequest.setUser(userRequest);
        Administrator administrator = this.mapper.toDomain(administratorRequest);
        Administrator savedAdministrator = this.service.createAdministrator(administrator);
        AdministratorResponse response = this.mapper.toUserResponse(savedAdministrator);
        AuthUserResponse authUserResponse = this.authService.createRegistrationToken(response.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(authUserResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdministratorResponse> updateAdministrator(@PathVariable Long id, @Valid @RequestBody AdministratorRequest administratorRequest) {
        Administrator administrator = this.mapper.toDomain(administratorRequest);
        Administrator updatedAdministrator = this.service.updateAdministrator(id, administrator);
        AdministratorResponse response = this.mapper.toUserResponse(updatedAdministrator);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdministratorById(@PathVariable Long id) {
        this.service.deleteAdministratorById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Administrador eliminado.");
    }
}